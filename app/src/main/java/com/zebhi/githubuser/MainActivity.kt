package com.zebhi.githubuser

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zebhi.githubuser.databinding.ActivityMainBinding
import com.zebhi.githubuser.ui.users.SearchUsersFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_USERS = ""
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSearchUsers.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                val intentFavorites = Intent(this, FavoriteActivity::class.java)
                startActivity(intentFavorites)
                return true
            }
            R.id.menu_setting -> {
                val intentSettings = Intent(this, SettingsActivity::class.java)
                startActivity(intentSettings)
                return true
            }
            else -> return true
        }
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.btn_search_users -> {

                val bundle = Bundle()
                val query: String = binding.etInputUsername.text.toString()
                bundle.putString(EXTRA_USERS, query)
                val searchFragment = SearchUsersFragment()
                searchFragment.arguments = bundle

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, searchFragment, query)
                    .commit()
            }
        }
    }
}

