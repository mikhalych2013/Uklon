package com.test.uklon.screens.user_details

import com.test.uklon.api.Api
import com.test.uklon.api.ApiError
import com.test.uklon.api.models.UserDetails
import io.reactivex.disposables.Disposable

class UserDetailsPresenter : UserDetailsContract.PresenterContract {

    private var view: UserDetailsContract.ViewContract? = null
    private var details: UserDetails? = null
    private var prepareDetailsDisposable: Disposable? = null

    override fun init(view: UserDetailsContract.ViewContract, userId: Long) {
        this.view = view
        view.itemsRefreshedObservable().subscribe { prepareDetails(userId) }
        if (details == null) prepareDetails(userId) else view.updateContent(details, false, null)
    }

    override fun release() {
        prepareDetailsDisposable?.dispose()
        view = null
    }

    private fun prepareDetails(userId: Long) {
        if (prepareDetailsDisposable?.isDisposed == false) return
        view?.updateContent(details, true, null)
        prepareDetailsDisposable = Api.instance.getUserDetails(userId)
//        .delaySubscription(2000, TimeUnit.MILLISECONDS)
        .subscribe ({
            details = it
            view?.updateContent(it, false, null)
        }, {
            view?.updateContent(details, false, ApiError(it).message)
        })
    }

}