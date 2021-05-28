package com.meghdut.tinode.db

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface Message : Entity<Message> {
    companion object : Entity.Factory<Message>()

    val id: Int
    val topic_id: String
    val user_id: String
    val status: Int
    val sender: String
    val ts: Int
    val seq: Int
    val high: Int
    val del_id: Int
    val head: String
    val content: String
}

object Messages : Table<Message>("messages") {
    val id = int("id").primaryKey().bindTo { it.id }
    val topic_id = varchar("topic_id").bindTo { it.topic_id }
    val user_id = varchar("user_id").bindTo { it.user_id }
    val status = int("status").bindTo { it.status }
    val sender = varchar("sender").bindTo { it.sender }
    val ts = int("ts").bindTo { it.ts }
    val seq = int("seq").bindTo { it.seq }
    val high = int("high").bindTo { it.high }
    val del_id = int("del_id").bindTo { it.del_id }
    val head = varchar("head").bindTo { it.head }
    val content = varchar("content").bindTo { it.content }
}


object MessagesDb {
    const val MESSAGE_PREVIEW_LENGTH = 80

    /**
     * The name of the main table.
     */
    const val TABLE_NAME = "messages"

    /**
     * Topic ID, references topics._ID
     */
    const val COLUMN_NAME_TOPIC_ID = "topic_id"

    /**
     * SQL statement to drop Messages table.
     */
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    const val COLUMN_IDX_ID = 0
    const val COLUMN_IDX_TOPIC_ID = 1
    const val COLUMN_IDX_USER_ID = 2
    const val COLUMN_IDX_STATUS = 3
    const val COLUMN_IDX_SENDER = 4
    const val COLUMN_IDX_TS = 5
    const val COLUMN_IDX_SEQ = 6
    const val COLUMN_IDX_HIGH = 7
    const val COLUMN_IDX_DEL_ID = 8
    const val COLUMN_IDX_HEAD = 9
    const val COLUMN_IDX_CONTENT = 10
    const val COLUMN_IDX_TOPIC_NAME = 11
    private const val TAG = "MessageDb"

    /**
     * Id of the originator of the message, references users._ID
     */
    const val COLUMN_NAME_USER_ID = "user_id"

    /**
     * Status of the message: unsent, delivered, deleted
     */
    const val COLUMN_NAME_STATUS = "status"

    /**
     * Uid as string. Deserialized here to avoid a join.
     */
    const val COLUMN_NAME_SENDER = "sender"

    /**
     * Message timestamp
     */
    const val COLUMN_NAME_TS = "ts"

    /**
     * Server-issued sequence ID, integer, indexed. If the message represents
     * a deleted range, then <tt>seq</tt> is the lowest bound of the range;
     * the bound is closed (inclusive).
     */
    const val COLUMN_NAME_SEQ = "seq"

    /**
     * If message represents a deleted range, this is the upper bound of the range, NULL otherwise.
     * The bound is open (exclusive).
     */
    const val COLUMN_NAME_HIGH = "high"

    /**
     * If message represents a deleted range, ID of the deletion record.
     */
    const val COLUMN_NAME_DEL_ID = "del_id"

    /**
     * Serialized header.
     */
    const val COLUMN_NAME_HEAD = "head"

    /**
     * Serialized message content
     */
    const val COLUMN_NAME_CONTENT = "content"

    /**
     * SQL statement to create Messages table
     */
    val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_TOPIC_ID
            + " REFERENCES " + TopicsDb.TABLE_NAME + "(" + _ID + ")," +
            COLUMN_NAME_USER_ID
            + " REFERENCES " + UsersDb.TABLE_NAME + "(" + _ID + ")," +
            COLUMN_NAME_STATUS + " INT," +
            COLUMN_NAME_SENDER + " TEXT," +
            COLUMN_NAME_TS + " INT," +
            COLUMN_NAME_SEQ + " INT," +
            COLUMN_NAME_HIGH + " INT," +
            COLUMN_NAME_DEL_ID + " INT," +
            COLUMN_NAME_HEAD + " TEXT," +
            COLUMN_NAME_CONTENT + " TEXT)")

    /**
     * Name of the topic when doing a join.
     */
    const val COLUMN_NAME_TOPIC_NAME = "topic"

    /**
     * The name of index: messages by topic and sequence.
     */
    const val INDEX_NAME = "message_topic_id_seq"

    /**
     * Drop the index too
     */
    const val DROP_INDEX = "DROP INDEX IF EXISTS $INDEX_NAME"

    /**
     * Add unique index on topic-seq, in descending order
     */
    const val CREATE_INDEX = "CREATE UNIQUE INDEX " + INDEX_NAME +
            " ON " + TABLE_NAME + " (" +
            COLUMN_NAME_TOPIC_ID + "," +
            COLUMN_NAME_SEQ + " DESC)"

}