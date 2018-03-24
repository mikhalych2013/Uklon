package com.test.uklon.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class ScreenManager(private val manager: FragmentManager) {

    fun setFragment(fragment: Fragment?, containerId: Int) {
        fragment?.also {
            val tag = generateFragmentTag(fragment)
            manager.beginTransaction().replace(containerId, fragment, tag).commit()
        }
    }

    fun putRootFragment(fragment: Fragment, containerId: Int, animSet: Array<Int>? = null) {
        val tag = generateFragmentTag(fragment)
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val transaction = manager.beginTransaction()
        if (animSet != null) when {
            animSet.size > 3 -> transaction.setCustomAnimations(animSet[0], animSet[1], animSet[2], animSet[3])
            animSet.size > 1 -> transaction.setCustomAnimations(animSet[0], animSet[1])
        }
        transaction.replace(containerId, fragment, tag).commit()
        manager.executePendingTransactions()
    }

    fun putFragment(fragment: Fragment, containerId: Int, animSet: Array<Int>? = null) {
        val tag = generateFragmentTag(fragment)
        val transaction = manager.beginTransaction()
        if (animSet != null) when {
            animSet.size > 3 -> transaction.setCustomAnimations(animSet[0], animSet[1], animSet[2], animSet[3])
            animSet.size > 1 -> transaction.setCustomAnimations(animSet[0], animSet[1])
        }
        transaction.replace(containerId, fragment, tag).addToBackStack(tag).commit()
    }

    fun hasPoppedFragments(): Boolean {
        return manager.backStackEntryCount > 0
    }

    fun popBackStack() {
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        manager.executePendingTransactions()
    }

    fun popFragment() {
        if (hasPoppedFragments()) manager.popBackStack()
    }

    fun popFragments(count: Int?, tag: String?, popAlways: Boolean = false) {
        for (index in manager.backStackEntryCount - 1 downTo 0) {
            val name = manager.getBackStackEntryAt(index).name
            if (count != null && count > 0 && index == manager.backStackEntryCount - count) {
                manager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                return
            } else if (tag?.isNotEmpty() == true && name.contains(tag)) {
                manager.popBackStack(name, 0)
                return
            }
        }
        if (popAlways) popBackStack()
    }

    fun performOnBackPressed(): Boolean {
        if (manager.backStackEntryCount > 0) {
            val tag = manager.getBackStackEntryAt(manager.backStackEntryCount - 1).name
            val topFragment = tag?.let { manager.findFragmentByTag(it) }
            (topFragment as? OnBackPressedListener)?.also { return it.onBackPressed() }
        }
        return false
    }

    private fun generateFragmentTag(fragment: Fragment): String {
        return "${fragment.javaClass.name}${'$'}${System.currentTimeMillis()}"
    }

}