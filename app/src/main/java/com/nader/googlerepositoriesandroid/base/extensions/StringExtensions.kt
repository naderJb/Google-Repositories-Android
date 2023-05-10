package com.nader.googlerepositoriesandroid.base.extensions

fun String?.safe(defaultValue: String = "") = this ?: defaultValue
