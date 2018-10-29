package id.gits.gitsmvvmkotlin.data.source

import android.util.Log
import com.google.gson.Gson
import id.gits.gitsmvvmkotlin.data.model.*
import id.gits.gitsmvvmkotlin.data.source.local.GitsLocalDataSource
import id.gits.gitsmvvmkotlin.data.source.remote.GitsRemoteDataSource

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

open class GitsRepository(val remoteDataSource: GitsRemoteDataSource,
                          val localDataSource: GitsLocalDataSource) : GitsDataSource {

    override fun getUserProfile(auth: String, callback: GitsDataSource.GetUserProfileCallback) {
        remoteDataSource.getUserProfile(auth, object : GitsDataSource.GetUserProfileCallback{
            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: UserProfile) {
                callback.onSuccess(data)
            }

            override fun onLoadDataFromLocal(data: UserProfile) {
                callback.onLoadDataFromLocal(data)
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

    override fun getLocalMessages(callback: GitsDataSource.GetLocalMessagesCallback) {
        localDataSource.getLocalMessages(object : GitsDataSource.GetLocalMessagesCallback {
            override fun onLoadDataFromLocal(data: ArrayList<LocalMessages>) {
                callback.onLoadDataFromLocal(data)
            }

            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: ArrayList<LocalMessages>) {
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

    override fun saveMessages(data: LocalMessages) {
        localDataSource.saveMessages(data)
    }

    override fun getMessages(auth: String, limit: Int, callback: GitsDataSource.GetMessagesCallback) {
        remoteDataSource.getMessages(auth, limit, object : GitsDataSource.GetMessagesCallback {
            override fun onLoadDataFromLocal(data: List<Messages>) {

            }

            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: List<Messages>) {
                var index : Int

                data.forEachIndexed { i, message ->
                    index = i

                    val localMessage = LocalMessages(message.asBCC, message.asCC, message.asMain,
                            message.attachmentCount, Gson().toJson(message.attachmentTypes), Gson().toJson(message.attachments),
                            message.attributeID, message.availabilityStatus, Gson().toJson(message.categories),
                            message.content, message.contentPreview, message.contentType, message.createdAt,
                            Gson().toJson(message.defaultLabels), message.deliveryStatus, message.editedAt.toString(),
                            Gson().toJson(message.extra), message.firstReadAt, message.hash, index,
                            message.inReplyTo, message.isArchived, message.isGroup, message.isImportant,
                            message.isTrashed, message.lastInteractionAt.toString(), message.lastReadAt,
                            message.messageClass, message.messageType, Gson().toJson(message.metaFields),
                            message.participantHash, message.readByRecipientAt.toString(), Gson().toJson(message.readerList),
                            message.receivedAt, message.recipientEmail, message.recipientID, Gson().toJson(message.recipients),
                            Gson().toJson(message.recipientsAsBCC), Gson().toJson(message.recipientsAsCC),
                            Gson().toJson(message.sender), message.senderEmail, message.sentAt, message.subject,
                            message.subjectPreview, Gson().toJson(message.tags), message.threadID, message.userID)

                    localDataSource.saveMessages(localMessage)
                }
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

    override fun saveUser(data: UserLogin) {
        localDataSource.saveUser(data)
    }

    override fun getUser(callback: GitsDataSource.GetLocalUserCallback) {
        localDataSource.getUser(object : GitsDataSource.GetLocalUserCallback {
            override fun onLoadDataFromLocal(data: UserLogin) {

            }

            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: UserLogin) {
                callback.onSuccess(data)
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

    override fun postUserLogin(identifier: String, password: String, callback: GitsDataSource.PostUserLoginCallback) {
        remoteDataSource.postUserLogin(identifier, password, object : GitsDataSource.PostUserLoginCallback {
            override fun onLoadDataFromLocal(data: Login) {

            }

            override fun onShowProgressDialog() {
                callback.onShowProgressDialog()
            }

            override fun onHideProgressDialog() {
                callback.onHideProgressDialog()
            }

            override fun onSuccess(data: Login) {
                callback.onSuccess(data)
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

    companion object {

        private var INSTANCE: GitsRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param gitsRemoteDataSource backend data source
         * *
         * @return the [GitsRepository] instance
         */
        @JvmStatic
        fun getInstance(gitsRemoteDataSource: GitsRemoteDataSource, gitsLocalDataSource: GitsLocalDataSource) =
                INSTANCE ?: synchronized(GitsRepository::class.java) {
                    INSTANCE ?: GitsRepository(gitsRemoteDataSource, gitsLocalDataSource)
                            .also { INSTANCE = it }
                }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}