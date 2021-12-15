package com.zebhi.githubuser.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zebhi.githubuser.R
import com.zebhi.githubuser.ui.followers.UserFollowersFragment
import com.zebhi.githubuser.ui.following.UserFollowingFragment
import java.util.*

class SectionsPagerAdapter(
    fm: FragmentManager,
    context: Context,
    username: String
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mContext = context

    private val pages = listOf(
        UserFollowersFragment(username),
        UserFollowingFragment(username)
    )

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    @ExperimentalStdlibApi
    override fun getPageTitle(position: Int): CharSequence? {
        val language: Locale = Locale.getDefault()

        return when (position) {

            0 -> mContext.getString(R.string.user_followers).capitalize(language)
            else -> mContext.getString(R.string.user_following).capitalize(language)
        }
    }
}

