package id.gits.gitsmvvmkotlin.data.source.remote

import id.gits.gitsmvvmkotlin.data.model.Login
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
object GitsRemoteDataSource : GitsDataSource {

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