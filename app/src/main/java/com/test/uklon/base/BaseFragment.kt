package com.test.uklon.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class BaseFragment : Fragment(), OnBackPressedListener {

    override fun onBackPressed(): Boolean = false

    fun putFragment(fragment: BaseFragment) {
        (activity as? IScreenStackManager)?.putFragment(fragment)
    }

    fun popFragment() {
        (activity as? IScreenStackManager)?.popFragment()
    }

    fun setTitle(resId: Int) {
        val actionBar = (activity as? AppCompatActivity)?.supportActionBar
        actionBar?.title = context?.getString(resId)
        actionBar?.setDisplayHomeAsUpEnabled((activity as? IScreenStackManager)?.hasPoppedFragments() ?: false)
    }

}