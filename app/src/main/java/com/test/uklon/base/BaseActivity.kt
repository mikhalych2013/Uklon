package com.test.uklon.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import com.test.uklon.R

abstract class BaseActivity : AppCompatActivity(), IScreenStackManager {

    protected var screenManager: ScreenManager? = null

    protected var animSet: Array<Int>? = null

    protected var containerId: Int? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animSet = arrayOf(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
        )
        containerId = android.R.id.content
        screenManager = ScreenManager(supportFragmentManager)
    }

    private fun hideSoftKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (currentFocus != null && imm?.isAcceptingText == true) {
            imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }

    override fun putFragment(fragment: Fragment) {
        hideSoftKeyBoard()
        containerId?.also { screenManager?.putFragment(fragment, it, animSet) }
    }

    override fun popFragment() {
        hideSoftKeyBoard()
        containerId?.also { screenManager?.popFragment() }
    }

    override fun setFragment(fragment: Fragment) {
        hideSoftKeyBoard()
        containerId?.also { screenManager?.setFragment(fragment, it) }
    }

    override fun hasPoppedFragments(): Boolean {
        return screenManager?.hasPoppedFragments() ?: false
    }

    override fun onBackPressed() {
        hideSoftKeyBoard()
        screenManager?.also { if (it.performOnBackPressed()) return }
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> { onBackPressed(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

}