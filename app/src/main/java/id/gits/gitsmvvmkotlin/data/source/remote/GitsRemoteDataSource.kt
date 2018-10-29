package id.gits.gitsmvvmkotlin.data.source.remote

import android.util.Log
import id.gits.gitsmvvmkotlin.data.model.*
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
object GitsRemoteDataSource : GitsDataSource {

    override fun getUserProfile(auth: String, callback: GitsDataSource.GetUserProfileCallback) {
        GitsApiService
                .getApiService
                .getUserProfile(auth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { callback.onShowProgressDialog() }
                .doOnComplete { callback.onHideProgressDialog() }
                .subscribe(object : ApiCallback<UserProfile>() {
                    override fun onSuccess(model: UserProfile) {
                        callback.onSuccess(model)
                    }

                    override fun onFailure(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onFinish() {
                        callback.onFinish()
                    }
                })
    }


    override fun getLocalMessages(callback: GitsDataSource.GetLocalMessagesCallback) {
        // Empty state
    }

    override fun saveMessages(data: LocalMessages) {
        // Empty state
    }

    override fun getMessages(auth: String, limit: Int, callback: GitsDataSource.GetMessagesCallback) {
        GitsApiService
                .getApiService
                .getMessages(auth, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { callback.onShowProgressDialog() }
                .doOnComplete { callback.onHideProgressDialog() }
                .subscribe(object : ApiCallback<List<Messages>>(){
                    override fun onSuccess(model: List<Messages>) {
                        if (model.isNotEmpty()) {
                            callback.onSuccess(model)
                        }
                    }

                    override fun onFailure(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onFinish() {
                        callback.onFinish()
                    }
                })
    }

    override fun saveUser(data: UserLogin) {
        // Empty state
    }

    override fun getUser(callback: GitsDataSource.GetLocalUserCallback) {
        // Empty state
    }

    override fun postUserLogin(identifier: String, password: String, callback: GitsDataSource.PostUserLoginCallback) {
        GitsApiService
                .getApiService
                .postLogin(identifier, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { callback.onShowProgressDialog() }
                .doOnComplete { callback.onHideProgressDialog() }
                .subscribe(object : ApiCallback<Login>() {
                    override fun onSuccess(model: Login) {
                        callback.onSuccess(model)
                    }

                    override fun onFailure(code: Int, errorMessage: String) {
                        callback.onFailed(code, errorMessage)
                    }

                    override fun onFinish() {
                        callback.onFinish()
                    }
                })
    }

}