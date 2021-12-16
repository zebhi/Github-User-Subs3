package com.zebhi.githubuser.presenter

import android.annotation.SuppressLint
import com.zebhi.githubuser.data.ApiCall
import com.zebhi.githubuser.ui.users.UsersViewInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UsersPresenterImpl(private val view: UsersViewInterface) : UsersPresenter {

    @SuppressLint("CheckResult")
    override fun searchUsersData(query: String) {
        view.loading(isloading = true)
        ApiCall.instance().searchUsersData(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    view.loading(false)
                    if (it.items!!.isNotEmpty()) {
                        view.success(it.items)
                    } else {
                        view.error("Data tidak tersedia")
                    }
                }, {
                    view.loading(false)
                    view.error(it.message!!)
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun getFollowersData(username: String) {
        view.loading(isloading = true)
        ApiCall.instance().getFollowersData(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    view.loading(false)
                    if (it.isNotEmpty()) {
                        view.success(it)
                    } else {
                        view.error("Data not available")
                    }
                }, {
                    view.loading(false)
                    view.error(it.message!!)
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun getFollowingData(username: String) {
        view.loading(isloading = true)
        ApiCall.instance().getFollowingData(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    view.loading(false)
                    if (it.isNotEmpty()) {
                        view.success(it)
                    } else {
                        view.error("Data not available")
                    }
                }, {
                    view.loading(false)
                    view.error(it.message!!)
                }
            )
    }
}
