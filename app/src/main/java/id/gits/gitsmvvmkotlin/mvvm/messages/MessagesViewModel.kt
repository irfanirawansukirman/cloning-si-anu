package id.gits.gitsmvvmkotlin.mvvm.messages

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import id.co.gits.gitsbase.BaseViewModel
import id.gits.gitsmvvmkotlin.data.model.LocalMessages
import id.gits.gitsmvvmkotlin.data.model.Messages
import id.gits.gitsmvvmkotlin.data.source.GitsDataSource
import id.gits.gitsmvvmkotlin.data.source.GitsRepository
import id.gits.gitsmvvmkotlin.util.SingleLiveEvent

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
class MessagesViewModel(application: Application, val repository: GitsRepository) : BaseViewModel(application) {

    val eventMessagesData = MutableLiveData<ArrayList<LocalMessages>>()

    fun getMessages(auth: String, limit: Int) {
        repository.getMessages(auth, limit, object : GitsDataSource.GetMessagesCallback {
            override fun onLoadDataFromLocal(data: List<Messages>) {

            }

            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<Messages>) {

            }

            override fun onFinish() {
                getLocalMessages()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                showLogError(MessagesViewModel::class.java.simpleName, errorMessage
                        ?: "Data not found")
            }
        })
    }

    fun getLocalMessages() {
        repository.getLocalMessages(object : GitsDataSource.GetLocalMessagesCallback {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: ArrayList<LocalMessages>) {

            }

            override fun onLoadDataFromLocal(data: ArrayList<LocalMessages>) {
                eventMessagesData.value = data
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }
}