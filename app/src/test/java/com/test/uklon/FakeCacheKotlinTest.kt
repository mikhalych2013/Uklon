package com.test.uklon

import com.test.uklon.api.models.Post
import com.test.uklon.api.models.User
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeCacheKotlinTest {

    private var fakeCache: FakeCache? = null

    @Before
    fun setUp() {
        fakeCache = FakeCache()
    }

    @After
    fun tearDown() {
        fakeCache = null
    }

    @Test(expected = IllegalStateException::class)
    fun test() {
        val posts = (0 until 10).map { Post(it.toLong(), it.toLong(), "title$it", "body$it") }

        fakeCache!!.setData(Post::class, posts)
        assertEquals(10, fakeCache!!.getData(Post::class).size)

        fakeCache!!.pushData(Post::class, posts)
        assertEquals(20, fakeCache!!.getData(Post::class).size)

        fakeCache!!.setData(Post::class, posts)
        assertEquals(10, fakeCache!!.getData(Post::class).size)

        fakeCache!!.getData(User::class)
    }

}