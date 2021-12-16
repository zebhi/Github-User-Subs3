package com.zebhi.githubuser.ui.users

import com.zebhi.githubuser.model.User

interface UserViewInterface {
    fun loading(isloading: Boolean)
    fun success(data: User?)
    fun error(message: String)
}
