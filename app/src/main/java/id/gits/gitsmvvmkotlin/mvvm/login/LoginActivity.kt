package id.gits.gitsmvvmkotlin.mvvm.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.base.BaseActivity
import id.gits.gitsmvvmkotlin.databinding.LoginActivityBinding
import id.gits.gitsmvvmkotlin.util.obtainViewModel
import id.gits.gitsmvvmkotlin.util.replaceFragmentInActivity

class LoginActivity : BaseActivity() {

    lateinit var viewBinding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        viewBinding.apply {
            setupLoginFragment()
        }
    }

    private fun setupLoginFragment() {
        replaceFragmentInActivity(LoginFragment.newInstance(), R.id.frame_container)
    }

    fun obtainViewModel(): LoginViewModel = obtainViewModel(LoginViewModel::class.java)

}
