package com.meghdut.tinode.db

object AccountsDb {

    const val TABLE_NAME = "accounts"

    /**
     * Statements to drop accounts table and index
     */
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    private const val COLUMN_NAME_UID = "uid"
    private const val COLUMN_NAME_ACTIVE = "last_active"
    private const val COLUMN_NAME_CRED_METHODS = "cred_methods"
    private const val COLUMN_NAME_DEVICE_ID = "device_id"

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