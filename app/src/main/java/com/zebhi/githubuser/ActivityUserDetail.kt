package com.zebhi.githubuser

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zebhi.githubuser.adapter.SectionsPagerAdapter
import com.zebhi.githubuser.databinding.ActivityUserDetailBinding
import com.zebhi.githubuser.model.User
import com.zebhi.githubuser.presenter.UsersPresenterImpl
import com.zebhi.githubuser.room.UsersDAO
import com.zebhi.githubuser.room.UsersDatabase
import com.zebhi.githubuser.room.table.UsersEntity
import com.zebhi.githubuser.ui.users.UsersViewInterface

class ActivityUserDetail(private var username: String) : AppCompatActivity(), UsersViewInterface {

    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var usersDao: UsersDAO
    private lateinit var users: User

    private var usersList: List<User> = arrayListOf()
    private var presenter = UsersPresenterImpl(this)

    companion object {
        const val EXTRA_USERS = "extra_users"
    }

    override fun loading(isloading: Boolean) {
        if (isloading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun success(data: List<User>?) {
        if (data != null) {
            usersList = data
        }

        binding.tvDetailName.text = users.name.toString()
        binding.tvDetailCompany.text = users.company.toString()
        binding.tvDetailLocation.text = users.location.toString()
        binding.tvDetailRepos.text = users.public_repos.toString()
        binding.tvDetailFollowers.text = users.followers.toString()
        binding.tvDetailFollowing.text = users.following.toString()

        val avatarUrl: String = users.avatar_url.toString()

        Glide.with(this)
            .load(avatarUrl)
            .centerCrop()
            .into(binding.ivDetailAvatar)

        Toast.makeText(this, "Total Data : ${data?.size}", Toast.LENGTH_SHORT).show()
    }

    override fun error(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        usersDao = UsersDatabase.getInstance(this)?.usersDao()!!

//        username = this.arguments?.getString(EXTRA_USERS).toString()

//        username = intent.getParcelableExtra(EXTRA_USERS)!!

//        val usernames = users.login.toString()

        presenter.getDetailData(username)

        binding.viewPager.adapter = SectionsPagerAdapter(supportFragmentManager, this, username)
        binding.tabs.setupWithViewPager(binding.viewPager, true)

        refreshFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                val intentFavorites = Intent(this, FavoriteActivity::class.java)
                startActivity(intentFavorites)
                true
            }
            R.id.menu_setting -> {
                val intentSettings = Intent(this, SettingsActivity::class.java)
                startActivity(intentSettings)
                true
            }
            else -> true
        }
    }

    fun favoriteClick(view: View) {
        if (view.id == R.id.add_to_favorite) {
            if (!isFavorite) {
                usersDao.insertUserFavorite(UsersEntity.entity(users))
            } else {
                usersDao.deleteUser(users.id)
            }
            refreshFavorite()
        }
    }

    private var isFavorite: Boolean = false

    private fun refreshFavorite() {
        isFavorite = if (usersDao.getUser(users.id) != null) {
            binding.addToFavorite.setImageResource(R.drawable.ic_favorite_full)
            true
        } else {
            binding.addToFavorite.setImageResource(R.drawable.ic_favorite_border)
            false
        }
    }

}
