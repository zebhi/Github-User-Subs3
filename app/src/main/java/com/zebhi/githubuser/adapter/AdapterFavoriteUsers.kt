package com.zebhi.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zebhi.githubuser.databinding.ItemFavoriteUsersBinding
import com.zebhi.githubuser.room.table.UsersEntity

class AdapterFavoriteUsers(
    private val list: List<UsersEntity>,
    private var clickListener: OnUsersClickListener
) :
    RecyclerView.Adapter<AdapterFavoriteUsers.Holder>() {

    class Holder(val binding: ItemFavoriteUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val binding =
            ItemFavoriteUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.tvFavUsernameUsers.text = list[position].login
        holder.binding.tvFavHtmlUrlUsers.text = list[position].html_url

        val avatarUrl = list[position].avatar_url

        Glide.with(holder.itemView)
            .load(avatarUrl)
            .centerCrop()
            .into(holder.binding.ivFavAvatarUsers)

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(list, position)
        }
    }

    interface OnUsersClickListener {
        fun onItemClick(list: List<UsersEntity>, position: Int)
    }
}
