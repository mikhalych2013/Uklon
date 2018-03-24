package com.test.uklon.screens.post_list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.test.uklon.api.models.Post
import com.test.uklon.screens.PostCardViewHolder

class PostListAdapter(private val itemSelectedBlock: (post: Post) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_POST = 0
    }

    private var items = mutableListOf<Pair<Int, Any?>>()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].first

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostCardViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is PostCardViewHolder -> {
                holder.update(items[position].second as Post)
                holder.setItemClickListener(itemSelectedBlock)
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder?) {
        when(holder) {
            is PostCardViewHolder -> holder.setItemClickListener(null)
        }
    }

    fun updateContent(posts: List<Post>?) {
        items.clear()
        posts?.also { items.addAll(it.map { Pair(VIEW_TYPE_POST, it) }) }
        notifyDataSetChanged()
    }

}

