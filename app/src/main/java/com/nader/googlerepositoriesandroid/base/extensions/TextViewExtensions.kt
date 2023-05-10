package com.nader.googlerepositoriesandroid.base.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nader.googlerepositoriesandroid.base.utils.DateUtils

@BindingAdapter("app:date")
fun TextView.formatDate(date: String?) {
    text = DateUtils.formatDate(date)
}