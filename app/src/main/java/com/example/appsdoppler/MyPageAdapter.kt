package com.example.appsdoppler

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class MyPageAdapter(private val context: Context, manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val frags = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    override fun getItem(pos: Int): Fragment {
        return frags.get(pos)
    }

    fun getTitle(pos: Int): String {
        return titles.get(pos)
    }

    override fun getCount(): Int {
        return frags.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        frags.add(fragment)
        titles.add(title)
    }

    public override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }

}