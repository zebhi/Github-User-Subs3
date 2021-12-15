package com.zebhi.githubuser

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zebhi.githubuser.databinding.ActivityFavoriteUserDetailBinding
import com.zebhi.githubuser.room.UsersDAO
import com.zebhi.githubuser.room.UsersDatabase
import com.zebhi.githubuser.room.table.UsersEntity

class ActivityFavoriteUserDetail : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserDetailBinding
    private lateinit var usersDAO: UsersDAO
    private lateinit var usersEntity: UsersEntity

    companion object {
        const val EXTRA_USERS = "extra_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        usersDAO = UsersDatabase.getInstance(this)?.usersDao()!!

        usersEntity = intent.getParcelableExtra(EXTRA_USERS)!!

        binding.tvDetailFavUsername.text = usersEntity.login
        binding.tvDetailFavHtmlUrl.text = usersEntity.html_url

        val avatarUrl = usersEntity.avatar_url

        Glide.with(this)
            .load(avatarUrl)
            .centerCrop()
            .into(binding.ivDetailFavAvatar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_setting -> {
                val intentSettings = Intent(this, SettingsActivity::class.java)
                startActivity(intentSettings)
                true
            }
            else -> true
        }
    }
}
