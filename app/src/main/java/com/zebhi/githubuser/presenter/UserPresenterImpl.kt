package com.zebhi.githubuser.presenter

import android.annotation.SuppressLint
import com.zebhi.githubuser.data.ApiCall
import com.zebhi.githubuser.ui.users.UserViewInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserPresenterImpl(private val view: UserViewInterface) : UserPresenter {
    @SuppressLint("CheckResult")
    override fun getDetailData(username: String) {
        view.loading(isloading = true)
        ApiCall.instance().getDetailData(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    view.loading(false)
                    if (it.id != null) {
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
