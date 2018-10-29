package id.gits.gitsmvvmkotlin.data.source

import id.gits.gitsmvvmkotlin.base.BaseDataSource
import id.gits.gitsmvvmkotlin.base.BaseDataSource.GitsResponseCallback
import id.gits.gitsmvvmkotlin.data.model.*
import kotlin.collections.ArrayList

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

interface GitsDataSource : BaseDataSource {

    fun saveUser(data: UserLogin)

    fun getUser(callback: GetLocalUserCallback)

    fun postUserLogin(identifier: String, password: String, callback: PostUserLoginCallback)

    fun saveMessages(data: LocalMessages)

    fun getMessages(auth: String, limit: Int, callback: GetMessagesCallback)

    fun getLocalMessages(callback: GetLocalMessagesCallback)

    fun getUserProfile(auth: String, callback: GetUserProfileCallback)

    interface GetUserProfileCallback : GitsResponseCallback<UserProfile>
    interface GetMessagesCallback : GitsResponseCallback<List<Messages>>
    interface GetLocalMessagesCallback : GitsResponseCallback<ArrayList<LocalMessages>>
    interface PostUserLoginCallback : GitsResponseCallback<Login>
    interface GetLocalUserCallback : GitsResponseCallback<UserLogin>

}