package id.gits.gitsmvvmkotlin.mvvm.messages

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.base.BaseFragment
import id.gits.gitsmvvmkotlin.databinding.MessagesFragmentBinding
import id.gits.gitsmvvmkotlin.util.GitsDividerItemDecoration
import id.gits.gitsmvvmkotlin.util.putArgs
import kotlinx.android.synthetic.main.messages_fragment.*

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */

class MessagesFragment : BaseFragment() {

    lateinit var viewBinding: MessagesFragmentBinding
    lateinit var viewModel: MessagesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = MessagesFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as MessagesActivity).obtainViewModel().apply {

            }
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@MessagesFragment)
        }

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMessagesViewModel()
        setupMessagesList()
    }

    private fun setupMessagesViewModel() {
        viewModel = viewBinding.viewModel!!

        viewModel.getMessages("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ" +
                "9.eyJleHAiOjE1NDA3MjY5NjEsImhhc2giOiJjZmI5MTE3Yi05OWJmLTRkZmUtOTUzOC" +
                "0zNDkxZjIyNTg2NzQiLCJpYXQiOjE1NDA0Njc3NjEsIm5hZiI6MTU0MzA1OTc2MX0.mj4TFQ" +
                "84iica7zTCwOncK7LDe71SJp_hqNIJYJdaJMQ", 10)
    }

    private fun setupMessagesList() {
        recycler_messages.apply {
            setHasFixedSize(true)
            addItemDecoration(GitsDividerItemDecoration(activity!!.getDrawable(R.drawable.cv_divider_item),
                    false, false))
            adapter = MessagesAdapter()
        }
    }

    companion object {
        fun newInstance() = MessagesFragment().putArgs { }
    }
}