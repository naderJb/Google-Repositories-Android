package com.nader.googlerepositoriesandroid.organizationrepositrories.data.remote

import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.QueryModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.SearchOrgModel

interface OrganisationsDataSource {
    suspend fun getAllRepos(
        organisation: String,
        perPage: Int,
        page: Int
    ): List<OrganisationModel>

    suspend fun searchRepos(
        query: QueryModel,
        perPage: Int,
        page: Int
    ): SearchOrgModel

}