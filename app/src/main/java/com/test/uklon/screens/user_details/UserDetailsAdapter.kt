package com.test.uklon.screens.user_details

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.test.uklon.api.models.Post
import com.test.uklon.api.models.User
import com.test.uklon.api.models.UserDetails
import com.test.uklon.screens.PostCardViewHolder

class UserDetailsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_USER_CARD = 0
        const val VIEW_TYPE_POST_CARD = 1
    }

    private var items = mutableListOf<Pair<Int, Any?>>()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].first

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_USER_CARD -> UserCardViewHolder.create(parent)
            else -> PostCardViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is UserCardViewHolder -> holder.update(items[position].second as User)
            is PostCardViewHolder -> holder.update(items[position].second as Post)
        }
    }

    fun updateContent(details: UserDetails?) {
        items.clear()
        details?.also {
            items.add(Pair(VIEW_TYPE_USER_CARD, it.user))
            items.addAll(it.posts.map { Pair(VIEW_TYPE_POST_CARD, it) })
        }
        notifyDataSetChanged()
    }

}