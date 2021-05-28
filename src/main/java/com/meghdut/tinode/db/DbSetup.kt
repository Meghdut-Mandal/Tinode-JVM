package com.meghdut.tinode.db

import org.ktorm.database.Database
import java.sql.Connection

var _COUNT = "_count"
var _ID = "_id"

fun Database.setupDb() {
    useConnection {
        it.runAll(AccountsDb.CREATE_TABLE, AccountsDb.CREATE_INDEX_1, AccountsDb.CREATE_INDEX_2)
        it.runAll(TopicsDb.CREATE_TABLE, TopicsDb.CREATE_INDEX)
        it.runAll(UsersDb.CREATE_TABLE, UsersDb.CREATE_INDEX)
        it.runAll(SubscribersDb.CREATE_TABLE, SubscribersDb.CREATE_INDEX)
        it.runAll(MessagesDb.CREATE_TABLE, MessagesDb.CREATE_INDEX)
    }
}

fun Connection.runAll(vararg querries: String) {
    val statement = createStatement()
    querries.forEach(statement::addBatch)
    statement.executeBatch()
}