package id.gits.gitsmvvmkotlin.mvvm.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.gits.gitsdriver.utils.GitsHelper
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.base.BaseFragment
import id.gits.gitsmvvmkotlin.databinding.LoginFragmentBinding
import id.gits.gitsmvvmkotlin.mvvm.messages.MessagesActivity
import id.gits.gitsmvvmkotlin.util.gone
import id.gits.gitsmvvmkotlin.util.putArgs
import id.gits.gitsmvvmkotlin.util.visible
import kotlinx.android.synthetic.main.login_fragment.*

/**
 * Dibuat oleh Irfan Irawan Sukirman
 * @Copyright 2018
 */
class LoginFragment : BaseFragment() {

    private lateinit var viewBinding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = LoginFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as LoginActivity).obtainViewModel().apply {
                eventStateProgressVisibility.observe(this@LoginFragment, Observer { isVisible ->
                    disableButtonSubmit(isVisible!!)
                    disableLoginForm(isVisible)
                    showProgress(isVisible)
                })
                eventStateNavigation.observe(this@LoginFragment, Observer { state ->
                    if (state!!) {
                        startActivity(Intent(context, MessagesActivity::class.java))
                        (activity as LoginActivity).finish()
                    }
                })
            }
        }

        viewBinding.let {
            it.viewModel = viewBinding.viewModel
            it.setLifecycleOwner(this@LoginFragment)
        }

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginViewModel()
        setupViewListener()
    }

    private fun setupLoginViewModel() {
        viewModel = viewBinding.viewModel!!

        viewModel.getUserData()
    }

    private fun setupViewListener() {
        var isValid: Boolean

        text_login_submit.setOnClickListener {
            val identifier = edit_login_email.text.toString()
            val password = edit_login_password.text.toString()

            if (!TextUtils.isEmpty(identifier)) {
                if (GitsHelper.Func.emailValidate(identifier)) {
                    isValid = true
                } else {
                    isValid = false
                    edit_login_email.error = "This is not email address"
                }
            } else {
                isValid = false
                edit_login_email.error = "Email cannot be empty"
            }

            if (TextUtils.isEmpty(password)) {
                isValid = false
                edit_login_password.error = "Password cannot be empty"
            }

            if (isValid) {
                viewModel.postUserLogin(identifier, password)
            }
        }
    }

    private fun disableButtonSubmit(isEnable: Boolean) {
        text_login_submit.isEnabled = !isEnable
        text_login_submit.text = if (!isEnable) "Login" else null
        text_login_submit.setBackgroundResource(
                if (isEnable) R.drawable.cv_fill_grey_light_round_4dp else R.drawable.cv_fill_orange_4dp
        )
    }

    private fun disableLoginForm(isEnable: Boolean) {
        edit_login_email.isEnabled = !isEnable
        edit_login_password.isEnabled = !isEnable
    }

    private fun showProgress(isShow: Boolean) {
        if (isShow) progress_login.visible() else progress_login.gone()
    }

    companion object {
        fun newInstance() = LoginFragment().putArgs { }
    }
}