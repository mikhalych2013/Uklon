package com.test.uklon.screens.post_list

import com.test.uklon.api.Api
import com.test.uklon.api.ApiError
import com.test.uklon.api.models.Post
import io.reactivex.disposables.Disposable

class PostListPresenter : PostListContract.PresenterContract {

    private var view: PostListContract.ViewContract? = null
    private var posts: List<Post>? = null
    private var preparePostsDisposable: Disposable? = null

    override fun init(view: PostListContract.ViewContract) {
        this.view = view
        view.itemSelectedObservable().subscribe { view.pushUserDetailsFragment(it.userId) }
        view.itemsRefreshedObservable().subscribe { preparePosts() }
        if (posts == null) preparePosts() else view.updateContent(posts, false, null)
    }

    override fun release() {
        preparePostsDisposable?.dispose()
        view = null
    }

    private fun preparePosts() {
        if (preparePostsDisposable?.isDisposed == false) return
        view?.updateContent(posts, true, null)
        preparePostsDisposable = Api.instance.getPosts()
//        .delaySubscription(2000, TimeUnit.MILLISECONDS)
        .subscribe ({
            posts = it
            view?.updateContent(it, false, null)
        }, {
            view?.updateContent(posts, false, ApiError(it).message)
        })
    }

}