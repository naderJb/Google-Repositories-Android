package com.nader.googlerepositoriesandroid.organizationrepositrories.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class LicenseModel(
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("spdx_id")
    val SPDXId: String?,
    @SerializedName("url")
    val url: String?
) : Parcelable