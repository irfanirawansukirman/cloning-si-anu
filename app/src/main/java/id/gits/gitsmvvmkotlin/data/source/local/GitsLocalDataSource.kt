package id.gits.gitsmvvmkotlin.data.source.local

import android.support.annotation.VisibleForTesting
import id.gits.gitsmvvmkotlin.data.model.LocalMessages
import id.gits.gitsmvvmkotlin.data.model.UserLogin
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.local.dao.MessagesDao
import id.gits.gitsmvvmkotlin.data.source.local.dao.UserDao
import id.gits.gitsmvvmkotlin.util.dbhelper.AppExecutors
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

class GitsLocalDataSource private constructor(private val appExecutors: AppExecutors,
                                              private val userDao: UserDao,
                                              private val messagesDao: MessagesDao) : GitsDataSource {

    override fun getUserProfile(auth: String, callback: GitsDataSource.GetUserProfileCallback) {
        // Empty state
    }

    override fun getMessages(auth: String, limit: Int, callback: GitsDataSource.GetMessagesCallback) {

    }

    override fun saveMessages(data: LocalMessages) {
        appExecutors.diskIO.execute {
            messagesDao.saveMessages(data)
        }
    }

    override fun getLocalMessages(callback: GitsDataSource.GetLocalMessagesCallback) {
        val single = messagesDao.getMessages()
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<List<LocalMessages>> {
                    override fun onSuccess(t: List<LocalMessages>) {
                        callback.onLoadDataFromLocal(t as java.util.ArrayList<LocalMessages>)
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {

                    }
                })

    }

    override fun saveUser(data: UserLogin) {
        appExecutors.diskIO.execute {
            userDao.saveUser(data)
        }
    }

    override fun getUser(callback: GitsDataSource.GetLocalUserCallback) {
        appExecutors.diskIO.execute {
            val userLoginData = userDao.getUser()

            appExecutors.mainThread.execute {
                if (userLoginData == null) {
                    callback.onFailed(404, "Data not found")
                } else {
                    callback.onSuccess(userLoginData)
                }
            }
        }
    }

    override fun postUserLogin(identifier: String, password: String, callback: GitsDataSource.PostUserLoginCallback) {
        // Empty state
    }

    companion object {

        private var INSTANCE: GitsLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, userDao: UserDao, messagesDao: MessagesDao): GitsLocalDataSource {
            if (INSTANCE == null) {
                synchronized(GitsLocalDataSource::javaClass) {
                    INSTANCE = GitsLocalDataSource(appExecutors, userDao, messagesDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}