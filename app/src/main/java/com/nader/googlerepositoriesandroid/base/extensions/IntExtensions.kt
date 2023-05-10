package com.nader.googlerepositoriesandroid.base.extensions

fun Int?.safe(defaultValue: Int = 0) = this ?: defaultValue