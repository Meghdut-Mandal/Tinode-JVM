package com.meghdut.tinode.db

import org.ktorm.database.Database
import org.ktorm.database.use
import org.ktorm.support.sqlite.SQLiteDialect
import java.sql.Connection
import java.sql.Types


var _COUNT = "_count"
var _ID = "id"

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


data class Tble(val name: String, val fields: List<Clmn>)
data class Clmn(val name: String, val type: Int, var isPrimary: Boolean)

fun main() {
    val db: Database = Database.connect("jdbc:sqlite:sample.db", dialect = SQLiteDialect())
    db.useConnection {
        val metaData = it.metaData
        val resultSet = metaData.getTables(null, null, null, arrayOf("TABLE"))
        System.out.println("----------------------------------");
        val tblList = mutableListOf<Tble>()
        while (resultSet.next()) {
            val tableName = resultSet.getString("TABLE_NAME")
            val columns = metaData.getColumns(null, null, tableName, null)
            val clmsList = mutableListOf<Clmn>()
            while (columns.next()) {
                val columnName = columns.getString("COLUMN_NAME")
                val datatype = columns.getString("DATA_TYPE")
                val isNullable = columns.getString("IS_NULLABLE")
                val is_autoIncrment = columns.getString("IS_AUTOINCREMENT")
                if (isNullable != "YES" || is_autoIncrment != "NO")
                    println("!!!!")
                clmsList.add(Clmn(columnName, datatype.toInt(), false))
            }
            metaData.getPrimaryKeys(null, null, tableName).use { primaryKeys ->
                while (primaryKeys.next()) {
                    val primaryKey = primaryKeys.getString("COLUMN_NAME")
//                    println("Primary key: $primaryKey")
                    clmsList.forEach {
                        if (it.name == primaryKey)
                            it.isPrimary = true
                    }
                }
            }
            tblList.add(Tble(tableName, clmsList))
        }

        tblList.forEach {
            codeGen(it)
        }

    }
}

fun codeGen(tble: Tble) {
    val entityName = tble.name[0].toUpperCase() + tble.name.substring(1, tble.name.length - 1)
    val objectsName = entityName + "s"
    val clms = tble.fields
    val entityRows = clms.map {
        """  val ${it.name} : ${if (it.type == Types.INTEGER) "Int" else "String"}   """
    }.joinToString(separator = "\n")
    val objectRows= clms.map {
       val tbaleType=  if (it.type == Types.INTEGER) "int" else "varchar"
       val primaryPart= if (it.isPrimary) ".primaryKey()" else ""
        """ val ${it.name} = $tbaleType("${it.name}")$primaryPart.bindTo{ it.${it.name} } """
    }.joinToString(separator = "\n")

    val entityCode= """
       |interface $entityName : Entity<$entityName> { 
        |companion object : Entity.Factory<$entityName>()
        $entityRows
        |}
    """.trimMargin("|")

    val objectsCode= """
        |object $objectsName : Table<$entityName>("${tble.name}") {
        $objectRows
        |}
    """.trimMargin("|")
    println(entityCode)
    println(objectsCode)
}

