package com.nader.googlerepositoriesandroid.organizationrepositrories.domain

import com.nader.googlerepositoriesandroid.base.model.APIResponse
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.QueryModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.SearchOrgModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.repo.OrganisationsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrganisationsUseCaseImpl @Inject constructor(
    private val organisationsRepo: OrganisationsRepo
) : OrganisationsUseCase {
    override suspend fun getAllRepos(
        organisation: String,
        perPage: Int,
        page: Int
    ): Flow<APIResponse<List<OrganisationModel>>> =
        organisationsRepo.getAllRepos(organisation, perPage, page)

    override suspend fun searchRepos(
        query: QueryModel,
        perPage: Int,
        page: Int
    ): Flow<APIResponse<SearchOrgModel>> = organisationsRepo.searchRepos(query, perPage, page)
}