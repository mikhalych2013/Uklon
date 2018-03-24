package com.test.uklon.screens

import android.os.Bundle
import com.test.uklon.base.BaseActivity
import com.test.uklon.screens.post_list.PostListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) setFragment(PostListFragment.newInstance())
    }

}
