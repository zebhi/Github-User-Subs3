package com.zebhi.githubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponse(

    @SerializedName("items")
    val items: List<User>? = null

)

@Parcelize
data class User(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("company")
    val company: String? = null,

    @SerializedName("location")
    val location: String? = null,

    @SerializedName("followers")
    val followers: Int? = null,

    @SerializedName("following")
    val following: Int? = null,

    @SerializedName("public_repos")
    val public_repos: Int? = null,

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("avatar_url")
    val avatar_url: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("html_url")
    val html_url: String? = null,

    @SerializedName("followers_url")
    val followers_url: String? = null,

    @SerializedName("following_url")
    val following_url: String? = null,

    @SerializedName("gists_url")
    val gists_url: String? = null,

    @SerializedName("starred_url")
    val starred_url: String? = null,

    @SerializedName("subscriptions_url")
    val subscriptions_url: String? = null,

    @SerializedName("organizations_url")
    val organizations_url: String? = null,

    @SerializedName("repos_url")
    val repos_url: String? = null,

    @SerializedName("events_url")
    val events_url: String? = null,

    @SerializedName("received_events_url")
    val received_events_url: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("site_admin")
    val site_admin: Boolean? = null,

    @SerializedName("score")
    val score: Double? = null
) : Parcelable
