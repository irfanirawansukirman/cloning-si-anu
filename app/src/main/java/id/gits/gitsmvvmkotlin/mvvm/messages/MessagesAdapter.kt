package id.gits.gitsmvvmkotlin.mvvm.messages

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.gson.Gson
import id.co.gits.gitsdriver.utils.GitsBindableAdapter
import id.co.gits.gitsdriver.utils.GitsHelper
import id.gits.gitsmvvmkotlin.BR
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.data.model.LocalMessages
import id.gits.gitsmvvmkotlin.data.model.Sender
import id.gits.gitsmvvmkotlin.databinding.MessagesItemBinding

class MessagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), GitsBindableAdapter<LocalMessages> {

    var data = ArrayList<LocalMessages>()

    override fun setRecyclerViewData(data: ArrayList<LocalMessages>) {
        this.data = data

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinding: MessagesItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent!!.context),
                R.layout.messages_item, parent, false)

        return MainItemRow(viewBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainItemRow).bindItem(data[position])
    }

    class MainItemRow(private val viewBinding: MessagesItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindItem(message: LocalMessages) {
            val sender = Gson().fromJson(message.sender, Sender::class.java)
            val createdAt = GitsHelper.Func.dateFormatFromTimeString(message.createdAt ?: "",
                    GitsHelper.Const.DATE_TIME_GLOBAL, GitsHelper.Const.TIME_GENERAL_HH_MM, false)
            viewBinding.apply {
                setVariable(BR.imageUrl, sender.avatarPathSmall)
                setVariable(BR.senderName, sender.fullName)
                setVariable(BR.messageTitle, message.subject)
                setVariable(BR.messageSubTitle, message.subjectPreview)
                setVariable(BR.timeMessage, createdAt)
                executePendingBindings()
            }
        }
    }
}