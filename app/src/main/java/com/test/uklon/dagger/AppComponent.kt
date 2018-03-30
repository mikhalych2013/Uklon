package com.test.uklon.dagger

import com.test.uklon.screens.post_list.PostListFragment
import com.test.uklon.screens.user_details.UserDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(target: PostListFragment)
    fun inject(target: UserDetailsFragment)
}