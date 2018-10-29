package id.gits.gitsmvvmkotlin.mvvm.messages

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.base.BaseActivity
import id.gits.gitsmvvmkotlin.databinding.MessagesActivityBinding
import id.gits.gitsmvvmkotlin.mvvm.messagecompose.MessageComposeActivity
import id.gits.gitsmvvmkotlin.mvvm.userprofile.UserProfileActivity
import id.gits.gitsmvvmkotlin.util.obtainViewModel
import id.gits.gitsmvvmkotlin.util.replaceFragmentInActivity
import id.gits.gitsmvvmkotlin.util.replaceFragmentInActivityWithBackStack
import kotlinx.android.synthetic.main.toolbar.*

class MessagesActivity : BaseActivity() {

    lateinit var viewBinding: MessagesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.messages_activity)
        viewBinding.apply {
            setupToolbar()
            setupMessagesFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.messages_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_create_message -> startActivity(Intent(this, MessageComposeActivity::class.java))
            R.id.action_open_profile -> startActivity(Intent(this, UserProfileActivity::class.java))
        }
        return false
    }

    private fun setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    fun setupMessagesFragment() {
        replaceFragmentInActivityWithBackStack(MessagesFragment.newInstance(),
                R.id.frame_container, MessagesActivity::class.java.simpleName)
    }

    fun obtainViewModel(): MessagesViewModel = obtainViewModel(MessagesViewModel::class.java)

}
