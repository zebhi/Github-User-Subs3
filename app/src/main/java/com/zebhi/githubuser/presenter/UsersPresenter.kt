package com.zebhi.githubuser.presenter

interface UsersPresenter {
    fun searchUsersData(query: String)
    fun getDetailData(username: String)
    fun getFollowersData(username: String)
    fun getFollowingData(username: String)
}
