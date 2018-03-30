package com.test.uklon.api

import com.test.uklon.api.models.Post
import com.test.uklon.api.models.UserDetails
import io.reactivex.Observable

/**
 * Created by Mikhalych on 30.03.2018.
 */
interface IApi {

    fun getPosts(): Observable<List<Post>>

    fun getUserDetails(userId: Long): Observable<UserDetails>

}