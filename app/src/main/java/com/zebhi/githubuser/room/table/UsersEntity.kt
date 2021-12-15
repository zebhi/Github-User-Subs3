package com.zebhi.githubuser.room.table

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.zebhi.githubuser.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users_table")
data class UsersEntity(

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,

    @SerializedName("company")
    @ColumnInfo(name = "company")
    var company: String? = null,

    @SerializedName("location")
    @ColumnInfo(name = "location")
    var location: String? = null,

    @SerializedName("followers")
    @ColumnInfo(name = "followers")
    var followers: Int? = null,

    @SerializedName("following")
    @ColumnInfo(name = "following")
    var following: Int? = null,

    @SerializedName("public_repos")
    @ColumnInfo(name = "public_repos")
    var public_repos: String? = null,

    @SerializedName("login")
    @ColumnInfo(name = "login")
    var login: String? = null,

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String? = null,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    var url: String? = null,

    @SerializedName("html_url")
    @ColumnInfo(name = "html_url")
    var html_url: String? = null,

    @SerializedName("followers_url")
    @ColumnInfo(name = "followers_url")
    var followers_url: String? = null,

    @SerializedName("following_url")
    @ColumnInfo(name = "following_url")
    var following_url: String? = null,

    @SerializedName("gists_url")
    @ColumnInfo(name = "gists_url")
    var gists_url: String? = null,

    @SerializedName("starred_url")
    @ColumnInfo(name = "starred_url")
    var starred_url: String? = null,

    @SerializedName("subscriptions_url")
    @ColumnInfo(name = "subscriptions_url")
    var subscriptions_url: String? = null,

    @SerializedName("organizations_url")
    @ColumnInfo(name = "organizations_url")
    var organizations_url: String? = null,

    @SerializedName("repos_url")
    @ColumnInfo(name = "repos_url")
    var repos_url: String? = null,

    @SerializedName("events_url")
    @ColumnInfo(name = "events_url")
    var events_url: String? = null,

    @SerializedName("received_events_url")
    @ColumnInfo(name = "received_events_url")
    var received_events_url: String? = null,

    @SerializedName("type")
    @ColumnInfo(name = "type")
    var type: String? = null

) : Parcelable {

    companion object {
        fun entity(u: User): UsersEntity {
            val entity = UsersEntity()
            entity.id = u.id
            entity.name = u.name.toString()
            entity.company = u.company.toString()
            entity.location = u.location.toString()
            entity.followers = u.followers
            entity.following = u.following
            entity.public_repos = u.public_repos.toString()
            entity.login = u.login.toString()
            entity.avatar_url = u.avatar_url.toString()
            entity.url = u.url.toString()
            entity.html_url = u.html_url.toString()
            entity.followers_url = u.followers_url.toString()
            entity.following_url = u.following_url.toString()
            entity.gists_url = u.gists_url.toString()
            entity.starred_url = u.starred_url.toString()
            entity.subscriptions_url = u.subscriptions_url.toString()
            entity.organizations_url = u.organizations_url.toString()
            entity.repos_url = u.repos_url.toString()
            entity.events_url = u.events_url.toString()
            entity.received_events_url = u.received_events_url.toString()
            entity.type = u.type.toString()

            return entity
        }
    }
}
