package com.nader.googlerepositoriesandroid.base.extensions

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.nader.googlerepositoriesandroid.R

@BindingAdapter("loadImage")
fun ShapeableImageView.loadImage(url: String?) {
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.ic_github)
        .error(R.drawable.ic_github)
        .fallback(R.drawable.ic_github)
        .into(this)
}
