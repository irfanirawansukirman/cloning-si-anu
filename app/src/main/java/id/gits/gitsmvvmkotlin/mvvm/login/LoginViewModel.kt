package id.gits.gitsmvvmkotlin.mvvm.login

import android.app.Application
import com.google.gson.Gson
import id.co.gits.gitsbase.BaseViewModel
import id.gits.gitsmvvmkotlin.data.model.Login
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.util.SingleLiveEvent

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
class LoginViewModel(application: Application, private val repository: GitsRepository) : BaseViewModel(application) {

    val eventStateProgressVisibility = SingleLiveEvent<Boolean>()
    val eventStateNavigation = SingleLiveEvent<Void>()

    fun postUserLogin(identifier: String, password: String) {
        repository.remoteDataSource.postUserLogin(identifier,
                password, object : GitsDataSource.PostUserLoginCallback {
            override fun onShowProgressDialog() {
                eventStateProgressVisibility.value = true
            }

            override fun onHideProgressDialog() {
                eventStateProgressVisibility.value = false
            }

            override fun onSuccess(data: Login) {
                showLogDebug("DATA", Gson().toJson(data))
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                if (statusCode == 400 || statusCode == 404 || statusCode == 500) {
                    eventStateProgressVisibility.value = false
                }
            }
        })
    }
}