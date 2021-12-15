package com.zebhi.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zebhi.githubuser.databinding.ItemRowUsersBinding
import com.zebhi.githubuser.model.LocalUserResponse

class AdapterLocalUsers(
    private val list: ArrayList<LocalUserResponse>,
    private var clickListener: OnUsersClickListener
) :
    RecyclerView.Adapter<AdapterLocalUsers.Holder>() {

    class Holder(val binding: ItemRowUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.tvNameUsers.text = list[position].name
        holder.binding.tvUsernameUsers.text = list[position].username
        holder.binding.tvCompanyUsers.text = list[position].company

        val avatarUrl = list[position].avatar

        Glide.with(holder.itemView)
            .load(avatarUrl)
            .centerCrop()
            .into(holder.binding.ivAvatarUsers)

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(list, position)
        }
    }


    interface OnUsersClickListener {
        fun onItemClick(list: ArrayList<LocalUserResponse>, position: Int)

    }
}
