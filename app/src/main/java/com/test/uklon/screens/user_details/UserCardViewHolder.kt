package com.test.uklon.screens.user_details

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.test.uklon.R
import com.test.uklon.api.models.User
import com.test.uklon.base.inflate
import kotlinx.android.synthetic.main.view_holder_user_card.view.*

class UserCardViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup): UserCardViewHolder = UserCardViewHolder(parent.inflate(R.layout.view_holder_user_card))
    }

    fun update(user: User) {
        itemView.text_view_username.text = user.username
        itemView.text_view_email.text = user.email
        itemView.text_view_phone.text = user.phone
    }

}