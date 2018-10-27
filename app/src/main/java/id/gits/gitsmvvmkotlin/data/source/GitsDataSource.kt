package id.gits.gitsmvvmkotlin.data.source

import id.gits.gitsmvvmkotlin.base.BaseDataSource
import id.gits.gitsmvvmkotlin.base.BaseDataSource.GitsResponseCallback
import id.gits.gitsmvvmkotlin.data.model.Login
import id.gits.gitsmvvmkotlin.data.model.UserLogin

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

interface GitsDataSource : BaseDataSource {

    fun saveUser(data: UserLogin)

    fun getUser(callback: GetLocalUserCallback)

    fun postUserLogin(identifier: String, password: String, callback: PostUserLoginCallback)

    interface PostUserLoginCallback : GitsResponseCallback<Login>

    interface GetLocalUserCallback : GitsResponseCallback<UserLogin>

}