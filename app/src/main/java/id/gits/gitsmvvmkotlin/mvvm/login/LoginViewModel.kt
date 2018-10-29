package id.gits.gitsmvvmkotlin.mvvm.login

import android.app.Application
import android.text.TextUtils
import com.google.gson.Gson
import id.co.gits.gitsbase.BaseViewModel
import id.gits.gitsmvvmkotlin.data.model.Login
import id.gits.gitsmvvmkotlin.data.model.UserLogin
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.util.SingleLiveEvent

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
class LoginViewModel(application: Application, private val repository: GitsRepository) : BaseViewModel(application) {

    val eventStateProgressVisibility = SingleLiveEvent<Boolean>()
    val eventStateNavigation = SingleLiveEvent<Boolean>()

    fun postUserLogin(identifier: String, password: String) {
        repository.remoteDataSource.postUserLogin(identifier,
                password, object : GitsDataSource.PostUserLoginCallback {
            override fun onLoadDataFromLocal(data: Login) {

            }

            override fun onShowProgressDialog() {
                eventStateProgressVisibility.value = true
            }

            override fun onHideProgressDialog() {
                eventStateProgressVisibility.value = false
            }

            override fun onSuccess(data: Login) {
                val userLogin = UserLogin(0, data.token, Gson().toJson(data.user))
                saveUserData(userLogin)
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

    fun saveUserData(data: UserLogin) {
        if (data != null) {
            repository.localDataSource.saveUser(data)

            eventStateNavigation.value = true
        }
    }

    fun getUserData() {
        repository.localDataSource.getUser(object : GitsDataSource.GetLocalUserCallback {
            override fun onLoadDataFromLocal(data: UserLogin) {

            }

            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: UserLogin) {
                if (data != null) {
                    if (!TextUtils.isEmpty(data.token)) {
                        eventStateNavigation.value = true
                    }
                }
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                showLogError(LoginViewModel::class.java.simpleName, errorMessage ?: "Data tidak ditemukan")
            }
        })
    }
}