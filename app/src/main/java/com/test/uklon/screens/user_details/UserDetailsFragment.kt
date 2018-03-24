package com.test.uklon.screens.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.uklon.R
import com.test.uklon.api.models.UserDetails
import com.test.uklon.base.BaseFragment
import com.test.uklon.base.MarginsItemDecoration
import com.test.uklon.base.onMainThread
import com.test.uklon.base.showToast
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_user_details.view.*

class UserDetailsFragment : BaseFragment(), UserDetailsContract.ViewContract {

    companion object {
        private const val KEY_USER_ID = "user_id"

        fun newInstance(userId: Long): UserDetailsFragment {
            val fragment = UserDetailsFragment()
            val bundle = Bundle()
            bundle.putLong(KEY_USER_ID, userId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var itemsRefreshedSubject: PublishSubject<Unit>

    private var presenter: UserDetailsContract.PresenterContract? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setTitle(R.string.fragment_user_details_screen_title)

        itemsRefreshedSubject = PublishSubject.create()
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)
        view.swipe_refresh_layout.setOnRefreshListener { itemsRefreshedSubject.onNext(Unit) }

        view.recycler_view.addItemDecoration(MarginsItemDecoration(context, R.dimen.list_item_vertical_margins, R.dimen.list_item_horizontal_margins))
        view.recycler_view.adapter = UserDetailsAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (presenter == null) presenter = UserDetailsPresenter()
        presenter?.init(this, arguments.getLong(KEY_USER_ID))
    }

    override fun updateContent(details: UserDetails?, showLoading: Boolean, errorMessage: String?) {
        (view?.recycler_view?.adapter as? UserDetailsAdapter)?.updateContent(details)
        view?.swipe_refresh_layout?.isRefreshing = showLoading
        if (errorMessage?.isNotEmpty() == true) showToast { errorMessage }
    }

    override fun itemsRefreshedObservable(): Observable<Unit> = itemsRefreshedSubject.onMainThread()

    override fun release() {
        itemsRefreshedSubject.onComplete()
        presenter?.release()
    }

}