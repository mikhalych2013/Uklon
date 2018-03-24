package com.test.uklon.screens

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.test.uklon.R
import com.test.uklon.api.models.Post
import com.test.uklon.base.inflate
import kotlinx.android.synthetic.main.view_holder_post_card.view.*

class PostCardViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): PostCardViewHolder = PostCardViewHolder(parent.inflate(R.layout.view_holder_post_card))
    }

    private var itemClickListener: ((Post) -> Unit)? = null

    fun update(post: Post) {
        itemView.text_view_post_id.text = post.userId.toString()
        itemView.text_view_title.text = post.title
        itemView.text_view_body.text = post.body
        itemView.setOnClickListener { itemClickListener?.invoke(post) }
    }

    fun setItemClickListener(block: ((Post) -> Unit)?) {
        itemClickListener = block
        if (itemClickListener == null) itemView.setOnClickListener(null)
    }

}