package com.zebhi.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zebhi.githubuser.databinding.ItemSearchUsersBinding
import com.zebhi.githubuser.model.User

class AdapterUsers(
    private val list: List<User>,
    private var clickListener: OnUsersClickListener
) :
    RecyclerView.Adapter<AdapterUsers.Holder>() {

    class Holder(val binding: ItemSearchUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ItemSearchUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.tvUsernameUsers.text = list[position].login
        holder.binding.tvHtmlUrlUsers.text = list[position].html_url

        val avatarUrl = list[position].avatar_url

        Glide.with(holder.itemView)
            .load(avatarUrl)
            .centerCrop()
            .into(holder.binding.ivAvatarUsers)

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(list, position)
        }
    }

    interface OnUsersClickListener {
        fun onItemClick(list: List<User>, position: Int)
    }
}
