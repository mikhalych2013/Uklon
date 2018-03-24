package com.test.uklon.screens.post_list

import com.test.uklon.api.models.Post
import io.reactivex.Observable

class PostListContract {

    interface ViewContract {
        fun updateContent(posts: List<Post>?, showLoading: Boolean, errorMessage: String?)
        fun pushUserDetailsFragment(userId: Long)
        fun itemSelectedObservable(): Observable<Post>
        fun itemsRefreshedObservable(): Observable<Unit>

        fun release()
    }

    interface PresenterContract {
        fun init(view: ViewContract)
        fun release()
    }

}