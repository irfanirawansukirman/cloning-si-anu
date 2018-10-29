package id.gits.gitsmvvmkotlin.mvvm.userprofile

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import id.gits.gitsmvvmkotlin.BR
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.base.BaseActivity
import id.gits.gitsmvvmkotlin.databinding.UserProfileActivityBinding
import id.gits.gitsmvvmkotlin.util.gone
import id.gits.gitsmvvmkotlin.util.obtainViewModel
import id.gits.gitsmvvmkotlin.util.visible
import kotlinx.android.synthetic.main.toolbar_transparent.*
import kotlinx.android.synthetic.main.user_profile_activity.*
import kotlinx.android.synthetic.main.user_profile_info.*
import kotlinx.android.synthetic.main.user_profile_office.*

class UserProfileActivity : BaseActivity() {

    lateinit var viewBinding: UserProfileActivityBinding
    lateinit var viewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.user_profile_activity)
        viewBinding.apply {
            setupUserProfileViewModel()
            setupViewListener()
            setupToolbar()
            getUserProfile()
        }
    }

    private fun setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun setupUserProfileViewModel() {
        viewModel = obtainViewModel()

        viewModel.apply {
            eventShowProgress.observe(this@UserProfileActivity, Observer { showProgress ->
                viewBinding.setVariable(BR.showProgress, showProgress)
            })
            eventUserProfileData.observe(this@UserProfileActivity, Observer { data ->
                text_userProfile_email.text = if (data?.email.isNullOrEmpty()) {
                    "dadang.kotz@gmail.com"
                } else {
                    data?.email
                }
                text_userProfile_phone.text = if (data?.mobilePhone.isNullOrEmpty()) {
                    "088229270639"
                } else {
                    data?.mobilePhone
                }
                text_userProfile_job.text = if (data?.jobTitle.isNullOrEmpty()) {
                    "Android Developer"
                } else {
                    data?.jobTitle
                }
                text_userProfile_department.text = if (data?.department.isNullOrEmpty()) {
                    "Mobile"
                } else {
                    data?.department
                }
            })
        }
    }

    private fun getUserProfile() {
        viewModel.getUserProfile()
    }

    private fun setupViewListener() {
        appbar_userProfile.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            when {
                Math.abs(verticalOffset) == appbar_userProfile.totalScrollRange -> {
                    frame_userProfile_imageContainer.gone()
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_orange)
                    txt_toolbar_title.setTextColor(ContextCompat.getColor(this, R.color.greyTitle))
                }
                Math.abs(verticalOffset) == 0 -> {
                    frame_userProfile_imageContainer.visible()
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_white)
                    txt_toolbar_title.setTextColor(ContextCompat.getColor(this, R.color.mainWhite))
                }
                else -> {
                    // Somewhere in between
                    // Do according to your requirement
                }
            }
        })
    }

    private fun obtainViewModel(): UserProfileViewModel = obtainViewModel(UserProfileViewModel::class.java)
}
