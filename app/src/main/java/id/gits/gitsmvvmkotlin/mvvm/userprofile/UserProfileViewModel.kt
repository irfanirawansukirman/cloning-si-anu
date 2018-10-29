package id.gits.gitsmvvmkotlin.mvvm.userprofile

import android.app.Application
import android.databinding.ObservableField
import android.util.Log
import com.google.gson.Gson
import id.co.gits.gitsbase.BaseViewModel
import id.gits.gitsmvvmkotlin.data.model.UserProfile
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.util.SingleLiveEvent

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
class UserProfileViewModel(application: Application, private val repository: GitsRepository) : BaseViewModel(application) {

    var eventUserProfileData = SingleLiveEvent<UserProfile>()

    fun getUserProfile() {
        repository.remoteDataSource.getUserProfile("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDEwMDE3ODQsImhhc2giOiJjZmI5MTE3Yi05OWJmLTRkZmUtOTUzOC0zNDkxZjIyNTg2NzQiLCJpYXQiOjE1NDA3NDI1ODQsIm5hZiI6MTU0MzMzNDU4NH0.4Muapa41wNCaiTExYhfuno6qFqD9S2rWHMb1VJ-rHgQ", object : GitsDataSource.GetUserProfileCallback {
            override fun onShowProgressDialog() {
                eventShowProgress.value = true
            }

            override fun onHideProgressDialog() {
                eventShowProgress.value = false
            }

            override fun onSuccess(data: UserProfile) {
                eventUserProfileData.value = data
            }

            override fun onLoadDataFromLocal(data: UserProfile) {

            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }
}