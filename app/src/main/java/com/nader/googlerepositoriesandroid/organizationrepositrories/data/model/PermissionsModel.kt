package com.nader.googlerepositoriesandroid.organizationrepositrories.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class PermissionsModel(
    @SerializedName("admin")
    val admin: Boolean?,
    @SerializedName("maintain")
    val maintain: Boolean?,
    @SerializedName("pull")
    val pull: Boolean?,
    @SerializedName("push")
    val push: Boolean?,
    @SerializedName("triage")
    val triage: Boolean?,
) : Parcelable