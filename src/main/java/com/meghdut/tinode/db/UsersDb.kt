package com.meghdut.tinode.db

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface User : Entity<User> {
    companion object : Entity.Factory<User>()

    val id: Int
    val account_id: String
    val uid: String
    val updated: Int
    val deleted: Int
    val pub: String
}

object Users : Table<User>("users") {
    val id = int("id").primaryKey().bindTo { it.id }
    val account_id = varchar("account_id").bindTo { it.account_id }
    val uid = varchar("uid").bindTo { it.uid }
    val updated = int("updated").bindTo { it.updated }
    val deleted = int("deleted").bindTo { it.deleted }
    val pub = varchar("pub").bindTo { it.pub }
}


object UsersDb {
    /**
     * The name of the main table.
     */
    const val TABLE_NAME = "users"

    /**
     * The name of index: topic by account id and topic name.
     */
    const val INDEX_NAME = "user_account_name"

    /**
     * Account ID, references accounts._ID
     */
    const val COLUMN_NAME_ACCOUNT_ID = "account_id"

    /**
     * Topic name, indexed
     */
    const val COLUMN_NAME_UID = "uid"

    /**
     * When the user was updated
     */
    const val COLUMN_NAME_UPDATED = "updated"

    /**
     * When the user was deleted
     */
    const val COLUMN_NAME_DELETED = "deleted"

    /**
     * Public user description, (what's shown in 'me' topic), serialized as TEXT
     */
    const val COLUMN_NAME_PUBLIC = "pub"

    // Pseudo-UID for messages with null From.
    const val UID_NULL = "none"
    const val COLUMN_IDX_ID = 0
    const val COLUMN_IDX_ACCOUNT_ID = 1
    const val COLUMN_IDX_UID = 2
    const val COLUMN_IDX_UPDATED = 3
    const val COLUMN_IDX_DELETED = 4
    const val COLUMN_IDX_PUBLIC = 5

    /**
     * SQL statement to create Messages table
     */
    val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_ACCOUNT_ID
            + " REFERENCES " + AccountsDb.TABLE_NAME + "(" + _ID + ")," +
            COLUMN_NAME_UID + " TEXT," +
            COLUMN_NAME_UPDATED + " INT," +
            COLUMN_NAME_DELETED + " INT," +
            COLUMN_NAME_PUBLIC + " TEXT)")

    /**
     * Add index on account_id-topic name, in descending order
     */
    const val CREATE_INDEX = "CREATE UNIQUE INDEX " + INDEX_NAME +
            " ON " + TABLE_NAME + " (" +
            COLUMN_NAME_ACCOUNT_ID + "," + COLUMN_NAME_UID + ")"

    /**
     * SQL statement to drop the table.
     */
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    /**
     * Drop the index too
     */
    const val DROP_INDEX = "DROP INDEX IF EXISTS $INDEX_NAME"

}