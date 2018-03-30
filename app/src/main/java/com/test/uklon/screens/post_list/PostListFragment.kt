package com.test.uklon.screens.post_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.uklon.App
import com.test.uklon.R
import com.test.uklon.api.models.Post
import com.test.uklon.base.BaseFragment
import com.test.uklon.base.MarginsItemDecoration
import com.test.uklon.base.onMainThread
import com.test.uklon.base.showToast
import com.test.uklon.screens.user_details.UserDetailsFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_post_list.view.*
import javax.inject.Inject

class PostListFragment : BaseFragment(), PostListContract.ViewContract {

    companion object {
        fun newInstance(): PostListFragment {
            val fragment = PostListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var itemSelectedSubject: PublishSubject<Post>
    private lateinit var itemsRefreshedSubject: PublishSubject<Unit>

    @Inject
    lateinit var presenter: PostListContract.PresenterContract

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setTitle(R.string.fragment_post_list_screen_title)

        (activity.application as App).appComponent.inject(this)
        itemSelectedSubject = PublishSubject.create()
        itemsRefreshedSubject = PublishSubject.create()
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)
        view.swipe_refresh_layout.setOnRefreshListener { itemsRefreshedSubject.onNext(Unit) }

        view.recycler_view.addItemDecoration(MarginsItemDecoration(context, R.dimen.list_item_vertical_margins, R.dimen.list_item_horizontal_margins))
        view.recycler_view.adapter = PostListAdapter { itemSelectedSubject.onNext(it) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init(this)
    }

    override fun onDestroyView() {
        release()
        super.onDestroyView()
    }

    override fun updateContent(posts: List<Post>?, showLoading: Boolean, errorMessage: String?) {
        (view?.recycler_view?.adapter as? PostListAdapter)?.updateContent(posts)
        view?.swipe_refresh_layout?.isRefreshing = showLoading
        if (errorMessage?.isNotEmpty() == true) showToast { errorMessage }
    }

    override fun pushUserDetailsFragment(userId: Long) {
        putFragment(UserDetailsFragment.newInstance(userId))
    }

    override fun itemSelectedObservable(): Observable<Post> = itemSelectedSubject.onMainThread()

    override fun itemsRefreshedObservable(): Observable<Unit> = itemsRefreshedSubject.onMainThread()

    override fun release() {
        itemSelectedSubject.onComplete()
        itemsRefreshedSubject.onComplete()
        presenter.release()
    }

}