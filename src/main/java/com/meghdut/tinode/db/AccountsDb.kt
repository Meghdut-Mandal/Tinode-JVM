package com.meghdut.tinode.db

import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.entity.*
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface Account : Entity<Account> {
    companion object : Entity.Factory<Account>()

    val id: Int
    var uid: String
    var last_active: Int
    var cred_methods: String
    var device_id: String
}

var Account.credMethods: List<String>
    get() = cred_methods.split(",")
    set(value) {
        cred_methods = value.joinToString(",")
    }

object Accounts : Table<Account>("accounts") {

    val id = int("id").primaryKey().bindTo { it.id }
    val uid = varchar("uid").bindTo { it.uid }
    val last_active = int("last_active").bindTo { it.last_active }
    val cred_methods = varchar("cred_methods").bindTo { it.cred_methods }
    val device_id = varchar("device_id").bindTo { it.device_id }
}

val Database.accounts get() = this.sequenceOf(Accounts)


fun Database.getDeviceToken(): String {
    return accounts.find { it.last_active eq 1 }?.device_id ?: ""
}

fun Database.setDeviceToken(token: String) {
    val account = accounts.find { it.last_active eq 1 } ?: return
    account.device_id = token
    account.flushChanges()
}

fun Database.deactivateAll() {
    accounts.forEach {
        it.last_active = 0
        it.flushChanges()
    }
}

fun Database.getByUid(uid: String): Account? {
    return accounts.find { it.uid eq uid }
}

fun Database.getActiveAccount(): Account? {
    return accounts.find { it.last_active eq 1 }
}

fun Database.deleteAccount(id: Int) {
    delete(Accounts) { it.id eq id }
}

fun Database.addOrActiveAccount(uid: String, methods: List<String>) {
    val foundAccount = getByUid(uid)
    deactivateAll()
    if (foundAccount != null) {
        foundAccount.last_active = 1
        foundAccount.flushChanges()
    } else {
        val account = Account {
            this.uid = uid
            this.credMethods = methods
            this.last_active = 1
        }
        accounts.add(account)
    }
}


object AccountsDb {

    const val TABLE_NAME = "accounts"

    /**
     * Statements to drop accounts table and index
     */
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    const val COLUMN_NAME_UID = "uid"
    const val COLUMN_NAME_ACTIVE = "last_active"
    const val COLUMN_NAME_CRED_METHODS = "cred_methods"
    const val COLUMN_NAME_DEVICE_ID = "device_id"

    /**
     * Statement to create account table - mapping of account UID to long id
     */
    val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_UID + " TEXT," +
            COLUMN_NAME_ACTIVE + " INTEGER," +
            COLUMN_NAME_CRED_METHODS + " TEXT," +
            COLUMN_NAME_DEVICE_ID + " TEXT)"
    private const val INDEX_UID = "accounts_uid"

    /**
     * Add index on account name
     */
    const val CREATE_INDEX_1 = "CREATE UNIQUE INDEX " + INDEX_UID +
            " ON " + TABLE_NAME + " (" +
            COLUMN_NAME_UID + ")"
    const val DROP_INDEX_1 = "DROP INDEX IF EXISTS $INDEX_UID"
    private const val INDEX_ACTIVE = "accounts_active"

    /**
     * Add index on last active
     */
    const val CREATE_INDEX_2 = "CREATE INDEX " + INDEX_ACTIVE +
            " ON " + TABLE_NAME + " (" +
            COLUMN_NAME_ACTIVE + ")"
    const val DROP_INDEX_2 = "DROP INDEX IF EXISTS $INDEX_ACTIVE"


}