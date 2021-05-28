package com.meghdut.tinode.db

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface Subscription : Entity<Subscription> {
    companion object : Entity.Factory<Subscription>()

    val id : Int
    val topic_id : String
    val user_id : String
    val status : Int
    val mode : String
    val updated : Int
    val deleted : Int
    val read : Int
    val recv : Int
    val clear : Int
    val last_seen : Int
    val user_agent : String
}
object Subscriptions : Table<Subscription>("subscriptions") {
    val id = int("id").primaryKey().bindTo{ it.id }
    val topic_id = varchar("topic_id").bindTo{ it.topic_id }
    val user_id = varchar("user_id").bindTo{ it.user_id }
    val status = int("status").bindTo{ it.status }
    val mode = varchar("mode").bindTo{ it.mode }
    val updated = int("updated").bindTo{ it.updated }
    val deleted = int("deleted").bindTo{ it.deleted }
    val read = int("read").bindTo{ it.read }
    val recv = int("recv").bindTo{ it.recv }
    val clear = int("clear").bindTo{ it.clear }
    val last_seen = int("last_seen").bindTo{ it.last_seen }
    val user_agent = varchar("user_agent").bindTo{ it.user_agent }
}



object SubscribersDb {
    /**
     * The name of the table.
     */
    const val TABLE_NAME = "subscriptions"

    /**
     * Topic _ID, references topics._id
     */
    const val COLUMN_NAME_TOPIC_ID = "topic_id"

    /**
     * SQL statement to drop the table.
     */
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    /**
     * The name of index: topic_id.
     */
    private const val INDEX_NAME = "subscription_topic_id"

    /**
     * Add index on topic_id
     */
    const val CREATE_INDEX = "CREATE INDEX " + INDEX_NAME +
            " ON " + TABLE_NAME + " (" + COLUMN_NAME_TOPIC_ID + ")"

    /**
     * Drop the index too
     */
    const val DROP_INDEX = "DROP INDEX IF EXISTS $INDEX_NAME"

    /**
     * UID of the subscriber
     */
    private const val COLUMN_NAME_USER_ID = "user_id"

    /**
     * Status of subscription: unsent, delivered, deleted
     */
    private const val COLUMN_NAME_STATUS = "status"

    /**
     * User's access mode
     */
    private const val COLUMN_NAME_MODE = "mode"

    /**
     * Last update timestamp
     */
    private const val COLUMN_NAME_UPDATED = "updated"

    /**
     * Deletion timestamp or null
     */
    private const val COLUMN_NAME_DELETED = "deleted"

    /**
     * Sequence ID marked as read by this user, integer
     */
    private const val COLUMN_NAME_READ = "read"

    /**
     * Sequence ID marked as received by this user, integer
     */
    private const val COLUMN_NAME_RECV = "recv"

    /**
     * Max sequence ID marked as deleted, integer
     */
    private const val COLUMN_NAME_CLEAR = "clear"

    /**
     * Time stamp when the user was last seen in the topic
     */
    private const val COLUMN_NAME_LAST_SEEN = "last_seen"

    /**
     * User agent string when last seen.
     */
    private const val COLUMN_NAME_USER_AGENT = "user_agent"

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
            COLUMN_NAME_MODE + " TEXT," +
            COLUMN_NAME_UPDATED + " INT," +
            COLUMN_NAME_DELETED + " INT," +
            COLUMN_NAME_READ + " INT," +
            COLUMN_NAME_RECV + " INT," +
            COLUMN_NAME_CLEAR + " INT," +
            COLUMN_NAME_LAST_SEEN + " INT," +
            COLUMN_NAME_USER_AGENT + " TEXT)")
    private const val TAG = "SubscriberDb"
    private const val COLUMN_IDX_ID = 0
    private const val COLUMN_IDX_TOPIC_ID = 1
    private const val COLUMN_IDX_USER_ID = 2
    private const val COLUMN_IDX_STATUS = 3
    private const val COLUMN_IDX_MODE = 4
    private const val COLUMN_IDX_UPDATED = 5
    private const val COLUMN_IDX_DELETED = 6
    private const val COLUMN_IDX_READ = 7
    private const val COLUMN_IDX_RECV = 8
    private const val COLUMN_IDX_CLEAR = 9
    private const val COLUMN_IDX_LAST_SEEN = 10
    private const val COLUMN_IDX_USER_AGENT = 11
    private const val JOIN_USER_COLUMN_IDX_UID = 12
    private const val JOIN_USER_COLUMN_IDX_PUBLIC = 13
    private const val JOIN_TOPIC_COLUMN_IDX_TOPIC = 14
    private const val JOIN_TOPIC_COLUMN_IDX_SEQ = 15

}