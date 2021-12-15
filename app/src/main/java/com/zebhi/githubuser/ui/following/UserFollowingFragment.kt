package com.zebhi.githubuser.ui.following

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zebhi.githubuser.ActivityUserDetail
import com.zebhi.githubuser.adapter.AdapterUsers
import com.zebhi.githubuser.databinding.FragmentUserFollowingBinding
import com.zebhi.githubuser.model.User
import com.zebhi.githubuser.presenter.UsersPresenterImpl
import com.zebhi.githubuser.ui.users.UsersViewInterface

/**
 * A simple [Fragment] subclass.
 */
class UserFollowingFragment(private var username: String) : Fragment(), UsersViewInterface,
    AdapterUsers.OnUsersClickListener {

    private var _binding: FragmentUserFollowingBinding? = null
    private val binding get() = _binding!!

    private var presenter = UsersPresenterImpl(this)
    private var usersList: List<User> = arrayListOf()

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

        val adapterUsers = AdapterUsers(usersList, this)

        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterUsers
            adapterUsers.notifyDataSetChanged()
        }
        binding.rvFollowing.setHasFixedSize(true)

        Toast.makeText(context, "Total Data : ${data?.size}", Toast.LENGTH_SHORT).show()
    }

    override fun error(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserFollowingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getFollowingData(username)
    }

    override fun onItemClick(list: List<User>, position: Int) {

        val users = User(
            login = list[position].login
        )

        val usersIntent = Intent(context, ActivityUserDetail::class.java)
        usersIntent.putExtra(ActivityUserDetail.EXTRA_USERS, users)
        startActivity(usersIntent)
    }

}
