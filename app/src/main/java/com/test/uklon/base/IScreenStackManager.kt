package com.test.uklon.base

import android.support.v4.app.Fragment

interface IScreenStackManager {

    fun putFragment(fragment: Fragment)

    fun popFragment()

    fun setFragment(fragment: Fragment)

    fun hasPoppedFragments(): Boolean

}