package com.test.uklon.screens.user_details

import com.test.uklon.api.models.UserDetails
import io.reactivex.Observable

class UserDetailsContract {

    interface ViewContract {
        fun updateContent(details: UserDetails?, showLoading: Boolean, errorMessage: String?)
        fun itemsRefreshedObservable(): Observable<Unit>

        fun release()
    }

    interface PresenterContract {
        fun init(view: ViewContract, userId: Long)
        fun release()
    }

}