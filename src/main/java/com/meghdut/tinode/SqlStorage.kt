package com.meghdut.tinode

import co.tinode.tinodesdk.Storage
import co.tinode.tinodesdk.Tinode
import co.tinode.tinodesdk.Topic
import co.tinode.tinodesdk.User
import co.tinode.tinodesdk.model.Drafty
import co.tinode.tinodesdk.model.MsgRange
import co.tinode.tinodesdk.model.MsgServerData
import co.tinode.tinodesdk.model.Subscription
import org.ktorm.database.Database
import org.ktorm.support.sqlite.SQLiteDialect
import java.io.Closeable
import java.util.*

class SqlStorage(val dbFileName: String) : Storage {

    val db = Database.connect("jdbc:sqlite:$dbFileName", dialect = SQLiteDialect())



    override fun getMyUid(): String {

        TODO("Not yet implemented")
    }

    override fun setMyUid(uid: String?) {
        TODO("Not yet implemented")
    }

    override fun setMyUid(uid: String?, credRequired: Array<out String>?) {
        TODO("Not yet implemented")
    }

    override fun deleteAccount(uid: String?) {
        TODO("Not yet implemented")
    }

    override fun getDeviceToken(): String {
        TODO("Not yet implemented")
    }

    override fun saveDeviceToken(token: String?) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    override fun setTimeAdjustment(adjustment: Long) {
        TODO("Not yet implemented")
    }

    override fun isReady(): Boolean {
        TODO("Not yet implemented")
    }

    override fun topicGetAll(tinode: Tinode?): Array<Topic<Any, Any, Any, Any>> {
        TODO("Not yet implemented")
    }

    override fun topicGet(tinode: Tinode?, name: String?): Topic<*, *, *, *> {
        TODO("Not yet implemented")
    }

    override fun topicAdd(topic: Topic<*, *, *, *>?): Long {
        TODO("Not yet implemented")
    }

    override fun topicUpdate(topic: Topic<*, *, *, *>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun topicDelete(topic: Topic<*, *, *, *>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun subAdd(topic: Topic<*, *, *, *>?, sub: Subscription<*, *>?): Long {
        TODO("Not yet implemented")
    }

    override fun subUpdate(topic: Topic<*, *, *, *>?, sub: Subscription<*, *>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun subNew(topic: Topic<*, *, *, *>?, sub: Subscription<*, *>?): Long {
        TODO("Not yet implemented")
    }

    override fun subDelete(topic: Topic<*, *, *, *>?, sub: Subscription<*, *>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSubscriptions(topic: Topic<*, *, *, *>?): MutableCollection<Subscription<Any, Any>> {
        TODO("Not yet implemented")
    }

    override fun userGet(uid: String?): User<*> {
        TODO("Not yet implemented")
    }

    override fun userAdd(user: User<*>?): Long {
        TODO("Not yet implemented")
    }

    override fun userUpdate(user: User<*>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgReceived(
        topic: Topic<*, *, *, *>?,
        sub: Subscription<*, *>?,
        msg: MsgServerData?
    ): Storage.Message {
        TODO("Not yet implemented")
    }

    override fun msgSend(
        topic: Topic<*, *, *, *>?,
        data: Drafty?,
        head: MutableMap<String, Any>?
    ): Storage.Message {
        TODO("Not yet implemented")
    }

    override fun msgDraft(
        topic: Topic<*, *, *, *>?,
        data: Drafty?,
        head: MutableMap<String, Any>?
    ): Storage.Message {
        TODO("Not yet implemented")
    }

    override fun msgDraftUpdate(
        topic: Topic<*, *, *, *>?,
        dbMessageId: Long,
        data: Drafty?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgReady(topic: Topic<*, *, *, *>?, dbMessageId: Long, data: Drafty?): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgSyncing(topic: Topic<*, *, *, *>?, dbMessageId: Long, sync: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgFailed(topic: Topic<*, *, *, *>?, dbMessageId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgPruneFailed(topic: Topic<*, *, *, *>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgDiscard(topic: Topic<*, *, *, *>?, dbMessageId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgDelivered(
        topic: Topic<*, *, *, *>?,
        dbMessageId: Long,
        timestamp: Date?,
        seq: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgMarkToDelete(
        topic: Topic<*, *, *, *>?,
        fromId: Int,
        toId: Int,
        markAsHard: Boolean
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgMarkToDelete(
        topic: Topic<*, *, *, *>?,
        ranges: Array<out MsgRange>?,
        markAsHard: Boolean
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgDelete(topic: Topic<*, *, *, *>?, delId: Int, fromId: Int, toId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgDelete(
        topic: Topic<*, *, *, *>?,
        delId: Int,
        ranges: Array<out MsgRange>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgRecvByRemote(sub: Subscription<*, *>?, recv: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun msgReadByRemote(sub: Subscription<*, *>?, read: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCachedMessagesRange(topic: Topic<*, *, *, *>?): MsgRange {
        TODO("Not yet implemented")
    }

    override fun getNextMissingRange(topic: Topic<*, *, *, *>?): MsgRange {
        TODO("Not yet implemented")
    }

    override fun setRead(topic: Topic<*, *, *, *>?, read: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun setRecv(topic: Topic<*, *, *, *>?, recv: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Storage.Message?> getMessageById(dbMessageId: Long): T {
        TODO("Not yet implemented")
    }

    override fun <T : Storage.Message?> getMessagePreviewById(dbMessageId: Long): T {
        TODO("Not yet implemented")
    }

    override fun <T : MutableIterator<Storage.Message>?> getLatestMessagePreviews(): T where T : Closeable? {
        TODO("Not yet implemented")
    }

    override fun <T : MutableIterator<Storage.Message>?> getQueuedMessages(topic: Topic<*, *, *, *>?): T where T : Closeable? {
        TODO("Not yet implemented")
    }

    override fun getQueuedMessageDeletes(
        topic: Topic<*, *, *, *>?,
        hard: Boolean
    ): Array<MsgRange> {
        TODO("Not yet implemented")
    }
}