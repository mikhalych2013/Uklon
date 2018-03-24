package com.test.uklon.api

import com.google.gson.GsonBuilder
import com.test.uklon.BuildConfig
import com.test.uklon.api.models.Post
import com.test.uklon.api.models.User
import com.test.uklon.api.models.UserDetails
import com.test.uklon.base.onApiThread
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    companion object {
        val instance: Api by lazy { Api() }
    }

    private var service: ApiService = createService()

    private fun createService(): ApiService {
        val httpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        httpClientBuilder.addInterceptor(logging)

        val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.client(httpClientBuilder.build())
        return builder.build().create(ApiService::class.java)
    }

    fun getPosts(): Observable<List<Post>> = service.getPosts().onApiThread()

    fun getUserDetails(userId: Long): Observable<UserDetails> {
        return Observable.zip(
                service.getUserById(userId),
                service.getPostsByUserId(userId),
                BiFunction { user: User, posts: List<Post> -> UserDetails(user, posts) }
        ).onApiThread()
    }

}