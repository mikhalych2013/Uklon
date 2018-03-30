package com.test.uklon.dagger

import com.test.uklon.api.IApi
import com.test.uklon.screens.post_list.PostListContract
import com.test.uklon.screens.post_list.PostListPresenter
import com.test.uklon.screens.user_details.UserDetailsContract
import com.test.uklon.screens.user_details.UserDetailsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun providePostListPresenter(api: IApi): PostListContract.PresenterContract = PostListPresenter(api)

    @Provides
    @Singleton
    fun provideUserDetailsPresenter(api: IApi): UserDetailsContract.PresenterContract = UserDetailsPresenter(api)
}