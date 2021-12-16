package com.zebhi.githubuser.data

import com.zebhi.githubuser.model.User
import com.zebhi.githubuser.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/users")
    fun searchUsersData(
        @Query("q") query: String
    ): Observable<UserResponse>

    @GET("users/{login}")
    fun getDetailData (
        @Path("login") username: String
    ): Observable<User>

    @GET("users/{login}/followers")
    fun getFollowersData(
        @Path("login") username: String
    ): Observable<List<User>>

    @GET("users/{login}/following")
    fun getFollowingData(
        @Path("login") username: String
    ): Observable<List<User>>

}
