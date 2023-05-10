package com.nader.googlerepositoriesandroid.base.utils

import com.nader.googlerepositoriesandroid.base.extensions.safe
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun formatDate(dateInput: String?, format: String = "dd-MM-yyyy"): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(dateInput.safe())

        val outputFormat = SimpleDateFormat(format, Locale.getDefault())
        return date?.let { outputFormat.format(date) }.safe()
    }
}