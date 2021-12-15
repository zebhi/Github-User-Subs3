package com.zebhi.githubuser.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zebhi.githubuser.ActivityUserDetail
import com.zebhi.githubuser.R
import com.zebhi.githubuser.adapter.AdapterLocalUsers
import com.zebhi.githubuser.databinding.FragmentListUsersBinding
import com.zebhi.githubuser.model.LocalUserResponse

class ListUsersFragment : Fragment(), AdapterLocalUsers.OnUsersClickListener {

    private var _binding: FragmentListUsersBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<LocalUserResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvListUsers.setHasFixedSize(true)

        list.addAll(getListUsers())
        showRecyclerList()
    }

    fun getListUsers(): ArrayList<LocalUserResponse> {
        val username = resources.getStringArray(R.array.username)
        val name = resources.getStringArray(R.array.name)
        val location = resources.getStringArray(R.array.location)
        val public_repos = resources.getIntArray(R.array.public_repos)
        val company = resources.getStringArray(R.array.company)
        val followers = resources.getIntArray(R.array.followers)
        val following = resources.getIntArray(R.array.following)
        val avatar = resources.getStringArray(R.array.avatar)

        val listUsers = ArrayList<LocalUserResponse>()
        for (position in name.indices) {
            val users = LocalUserResponse(
                username[position],
                name[position],
                location[position],
                public_repos[position],
                company[position],
                followers[position],
                following[position],
                avatar[position]
            )
            listUsers.add(users)
        }
        return listUsers
    }

    private fun showRecyclerList() {
        binding.rvListUsers.layoutManager = LinearLayoutManager(context)
        val listUsersAdapter = AdapterLocalUsers(list, this)
        binding.rvListUsers.adapter = listUsersAdapter
    }

    override fun onItemClick(list: ArrayList<LocalUserResponse>, position: Int) {
        val users = LocalUserResponse(
            username = list[position].username,
            name = list[position].name,
            location = list[position].location,
            public_repos = list[position].public_repos,
            company = list[position].company,
            followers = list[position].followers,
            following = list[position].following,
            avatar = list[position].avatar
        )

        val usersIntent = Intent(context, ActivityUserDetail::class.java)
        usersIntent.putExtra(ActivityUserDetail.EXTRA_USERS, users)
        startActivity(usersIntent)
    }
}
