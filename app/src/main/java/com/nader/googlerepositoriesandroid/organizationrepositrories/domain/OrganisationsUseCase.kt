package com.nader.googlerepositoriesandroid.organizationrepositrories.domain

import com.nader.googlerepositoriesandroid.base.model.APIResponse
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.QueryModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.SearchOrgModel
import kotlinx.coroutines.flow.Flow

interface OrganisationsUseCase {
    suspend fun getAllRepos(
        organisation: String,
        perPage: Int,
        page: Int
    ): Flow<APIResponse<List<OrganisationModel>>>

    suspend fun searchRepos(
        query: QueryModel,
        perPage: Int,
        page: Int
    ): Flow<APIResponse<SearchOrgModel>>
}