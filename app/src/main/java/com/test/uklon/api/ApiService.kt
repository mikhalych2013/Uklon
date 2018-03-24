package com.test.uklon.api

import com.test.uklon.api.models.Post
import com.test.uklon.api.models.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/posts")
    fun getPostsByUserId(@Query("userId") userId: Long): Observable<List<Post>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Long): Observable<User>

}