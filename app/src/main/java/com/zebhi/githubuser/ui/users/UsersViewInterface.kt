package com.zebhi.githubuser.ui.users

import com.zebhi.githubuser.model.User

interface UsersViewInterface {
    fun loading(isloading: Boolean)
    fun success(data: List<User>?)
    fun error(message: String)
}
