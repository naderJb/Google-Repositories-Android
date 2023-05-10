package com.nader.googlerepositoriesandroid.base.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorModel(
    @SerializedName("message")
    val message: String?
)