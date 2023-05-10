package com.nader.googlerepositoriesandroid.base.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.shouldShowFap(shouldShow: (Boolean) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0) {
                shouldShow.invoke(true)
            } else if (dy < 0 && recyclerView.computeVerticalScrollOffset() == 0) {
                shouldShow.invoke(false)
            }
        }
    })
}