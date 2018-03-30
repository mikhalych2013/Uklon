package com.test.uklon.dagger

import com.test.uklon.api.Api
import com.test.uklon.api.IApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideApi(): IApi = Api()
}