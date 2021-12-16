package com.zebhi.githubuser

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zebhi.githubuser.adapter.AdapterFavoriteUsers
import com.zebhi.githubuser.databinding.ActivityFavoriteBinding
import com.zebhi.githubuser.room.UsersDAO
import com.zebhi.githubuser.room.UsersDatabase
import com.zebhi.githubuser.room.table.UsersEntity

class FavoriteActivity : AppCompatActivity(), AdapterFavoriteUsers.OnUsersClickListener {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var usersDAO: UsersDAO
    private var usersEntity: List<UsersEntity> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        usersDAO = UsersDatabase.getInstance(this)?.usersDao()!!

        usersEntity = usersDAO.getAllUsers()
        initGetAllData(usersEntity)
    }

    private fun initGetAllData(usersEntity: List<UsersEntity>) {
        val adapterFavoriteUsers = AdapterFavoriteUsers(usersEntity, this)

        binding.rvFavoriteUsers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            if (usersEntity != null) {
                adapter = adapterFavoriteUsers
            } else {
                Toast.makeText(context, "Tidak ada Data Favorite", Toast.LENGTH_SHORT).show()
            }
            adapterFavoriteUsers.notifyDataSetChanged()
        }
        binding.rvFavoriteUsers.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_setting -> {
                val intentSettings = Intent(this, SettingsActivity::class.java)
                startActivity(intentSettings)
                return true
            }
            else -> return true
        }
    }

    override fun onItemClick(list: List<UsersEntity>, position: Int) {

        val users = list[position].login

        val usersIntent = Intent(this, ActivityUserDetail::class.java)
        usersIntent.putExtra(ActivityUserDetail.EXTRA_USERS, users)
        startActivity(usersIntent)
    }
}
