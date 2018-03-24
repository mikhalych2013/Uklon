package com.test.uklon.base

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginsItemDecoration(context: Context, verticalMarginsResId: Int, horizontalMarginsResId: Int) : RecyclerView.ItemDecoration() {

    private val verticalMargins: Int = context.resources.getDimensionPixelOffset(verticalMarginsResId)
    private val horizontalMargins: Int = context.resources.getDimensionPixelOffset(horizontalMarginsResId)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) outRect.top = verticalMargins
        outRect.bottom = verticalMargins
        outRect.left = horizontalMargins
        outRect.right = horizontalMargins
    }

}