package com.meghdut.tinode.db

object TopicsDb {

    /**
     * The name of the main table.
     */
    const val TABLE_NAME = "topics"

    /**
     * The name of index: topic by account id and topic name.
     */
    const val INDEX_NAME = "topic_account_name"

    /**
     * Account ID, references accounts._ID
     */
    const val COLUMN_NAME_ACCOUNT_ID = "account_id"

    /**
     * Topic sync status: queued, synced, deleted
     */
    const val COLUMN_NAME_STATUS = "status"

    /**
     * Topic name, indexed
     */
    const val COLUMN_NAME_TOPIC = "name"

    /**
     * When the topic was created
     */
    const val COLUMN_NAME_CREATED = "created"

    /**
     * When the topic was last updated
     */
    const val COLUMN_NAME_UPDATED = "updated"

    /**
     * Sequence ID marked as read by the current user, integer
     */
    const val COLUMN_NAME_READ = "read"

    /**
     * Sequence ID marked as received by the current user on any device (server-reported), integer
     */
    const val COLUMN_NAME_RECV = "recv"

    /**
     * Server-issued sequence ID, integer, indexed
     */
    const val COLUMN_NAME_SEQ = "seq"

    /**
     * Highest known ID of a delete transaction
     */
    const val COLUMN_NAME_CLEAR = "clear"

    /**
     * ID of the last applied delete transaction
     */
    const val COLUMN_NAME_MAX_DEL = "max_del"

    /**
     * Access mode, string
     */
    const val COLUMN_NAME_ACCESSMODE = "mode"

    /**
     * Default access mode (auth, anon)
     */
    const val COLUMN_NAME_DEFACS = "defacs"

    /**
     * Timestamp of the last message
     */
    const val COLUMN_NAME_LASTUSED = "last_used"

    /**
     * Minimum sequence ID received by the current device (self/locally-tracked), integer
     */
    const val COLUMN_NAME_MIN_LOCAL_SEQ = "min_local_seq"

    /**
     * Maximum sequence ID received by the current device (self/locally-tracked), integer
     */
    const val COLUMN_NAME_MAX_LOCAL_SEQ = "max_local_seq"

    /**
     * Seq ID to use for the next pending message.
     */
    const val COLUMN_NAME_NEXT_UNSENT_SEQ = "next_unsent_seq"

    /**
     * Topic tags, array of strings.
     */
    const val COLUMN_NAME_TAGS = "tags"

    /**
     * Timestamp when the topic was last online.
     */
    const val COLUMN_NAME_LAST_SEEN = "last_seen"

    /**
     * User agent of the last client when the topic was last online.
     */
    const val COLUMN_NAME_LAST_SEEN_UA = "last_seen_ua"

    /**
     * MeTopic credentials, serialized as TEXT.
     */
    const val COLUMN_NAME_CREDS = "creds"

    /**
     * Public topic description, serialized as TEXT
     */
    const val COLUMN_NAME_PUBLIC = "pub"

    /**
     * Private topic description, serialized as TEXT
     */
    const val COLUMN_NAME_PRIVATE = "priv"
    const val COLUMN_IDX_ID = 0
    const val COLUMN_IDX_ACCOUNT_ID = 1
    const val COLUMN_IDX_STATUS = 2
    const val COLUMN_IDX_TOPIC = 3
    const val COLUMN_IDX_CREATED = 4
    const val COLUMN_IDX_UPDATED = 5
    const val COLUMN_IDX_READ = 6
    const val COLUMN_IDX_RECV = 7
    const val COLUMN_IDX_SEQ = 8
    const val COLUMN_IDX_CLEAR = 9
    const val COLUMN_IDX_MAX_DEL = 10
    const val COLUMN_IDX_ACCESSMODE = 11
    const val COLUMN_IDX_DEFACS = 12
    const val COLUMN_IDX_LASTUSED = 13
    const val COLUMN_IDX_MIN_LOCAL_SEQ = 14
    const val COLUMN_IDX_MAX_LOCAL_SEQ = 15
    const val COLUMN_IDX_NEXT_UNSENT_SEQ = 16
    const val COLUMN_IDX_TAGS = 17
    const val COLUMN_IDX_LAST_SEEN = 18
    const val COLUMN_IDX_LAST_SEEN_UA = 19
    const val COLUMN_IDX_CREDS = 20
    const val COLUMN_IDX_PUBLIC = 21
    const val COLUMN_IDX_PRIVATE = 22

    /**
     * SQL statement to create Messages table
     */
    val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_ACCOUNT_ID
            + " REFERENCES " + AccountsDb.TABLE_NAME + "(" + _ID + ")," +
            COLUMN_NAME_STATUS + " INT," +
            COLUMN_NAME_TOPIC + " TEXT," +
            COLUMN_NAME_CREATED + " INT," +
            COLUMN_NAME_UPDATED + " INT," +
            COLUMN_NAME_READ + " INT," +
            COLUMN_NAME_RECV + " INT," +
            COLUMN_NAME_SEQ + " INT," +
            COLUMN_NAME_CLEAR + " INT," +
            COLUMN_NAME_MAX_DEL + " INT," +
            COLUMN_NAME_ACCESSMODE + " TEXT," +
            COLUMN_NAME_DEFACS + " TEXT," +
            COLUMN_NAME_LASTUSED + " INT," +
            COLUMN_NAME_MIN_LOCAL_SEQ + " INT," +
            COLUMN_NAME_MAX_LOCAL_SEQ + " INT," +
            COLUMN_NAME_NEXT_UNSENT_SEQ + " INT," +
            COLUMN_NAME_TAGS + " TEXT," +
            COLUMN_NAME_LAST_SEEN + " INT," +
            COLUMN_NAME_LAST_SEEN_UA + " TEXT," +
            COLUMN_NAME_CREDS + " TEXT," +
            COLUMN_NAME_PUBLIC + " TEXT," +
            COLUMN_NAME_PRIVATE + " TEXT)")

    /**
     * Add index on account_id-topic name, in descending order
     */
    const val CREATE_INDEX = "CREATE UNIQUE INDEX " + INDEX_NAME +
            " ON " + TABLE_NAME + " (" +
            COLUMN_NAME_ACCOUNT_ID + "," + COLUMN_NAME_TOPIC + ")"

    /**
     * SQL statement to drop the table.
     */
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    /**
     * Drop the index too
     */
    const val DROP_INDEX = "DROP INDEX IF EXISTS $INDEX_NAME"
    private const val TAG = "TopicsDb"
    private const val UNSENT_ID_START = 2000000000

}