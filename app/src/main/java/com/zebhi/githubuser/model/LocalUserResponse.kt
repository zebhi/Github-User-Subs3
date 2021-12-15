package com.zebhi.githubuser.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalUserResponse(
    var username: String,
    var name: String,
    var location: String,
    var public_repos: Int,
    var company: String,
    var followers: Int,
    var following: Int,
    var avatar: String
) : Parcelable
