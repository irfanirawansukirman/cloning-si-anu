package id.gits.gitsmvvmkotlin.data.source.local

import android.support.annotation.VisibleForTesting
import id.gits.gitsmvvmkotlin.data.model.UserLogin
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.local.movie.UserDao
import id.gits.gitsmvvmkotlin.util.dbhelper.AppExecutors

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

class GitsLocalDataSource private constructor(private val appExecutors: AppExecutors,
                                              private val userDao: UserDao) : GitsDataSource {

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
        fun getInstance(appExecutors: AppExecutors, userDao: UserDao): GitsLocalDataSource {
            if (INSTANCE == null) {
                synchronized(GitsLocalDataSource::javaClass) {
                    INSTANCE = GitsLocalDataSource(appExecutors, userDao)
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