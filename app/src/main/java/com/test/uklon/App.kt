package com.test.uklon

import android.app.Application
import com.test.uklon.dagger.AppComponent
import com.test.uklon.dagger.AppModule
import com.test.uklon.dagger.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = initDagger(this)
    }

    private fun initDagger(app: App): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()

}