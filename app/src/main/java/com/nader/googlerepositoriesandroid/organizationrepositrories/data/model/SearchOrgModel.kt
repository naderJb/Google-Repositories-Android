package com.nader.googlerepositoriesandroid.organizationrepositrories.data.model

import com.google.gson.annotations.SerializedName

data class SearchOrgModel(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<OrganisationModel>?,
)
