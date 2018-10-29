package id.gits.gitsmvvmkotlin.util

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.mvvm.login.LoginViewModel
import id.gits.gitsmvvmkotlin.mvvm.messages.MessagesViewModel
import id.gits.gitsmvvmkotlin.mvvm.userprofile.UserProfileViewModel

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

class ViewModelFactory private constructor(
        private val mApplication: Application,
        private val gitsRepository: GitsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(LoginViewModel::class.java) ->
                        LoginViewModel(mApplication, gitsRepository)
                    isAssignableFrom(MessagesViewModel::class.java) ->
                        MessagesViewModel(mApplication, gitsRepository)
                    isAssignableFrom(UserProfileViewModel::class.java) ->
                        UserProfileViewModel(mApplication, gitsRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(mApplication,
                            Injection.provideGitsRepository(mApplication.applicationContext))
                            .also { INSTANCE = it }
                }
    }
}