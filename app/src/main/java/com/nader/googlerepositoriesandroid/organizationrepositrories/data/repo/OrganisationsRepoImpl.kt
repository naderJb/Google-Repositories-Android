package com.nader.googlerepositoriesandroid.organizationrepositrories.data.repo

import com.nader.googlerepositoriesandroid.base.model.APIResponse
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.QueryModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.SearchOrgModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.remote.OrganisationsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrganisationsRepoImpl @Inject constructor(
    private val organisationsDataSource: OrganisationsDataSource
) : OrganisationsRepo {
    override suspend fun getAllRepos(
        organisation: String,
        perPage: Int,
        page: Int
    ): Flow<APIResponse<List<OrganisationModel>>> = flow {
        emit(APIResponse.loading())
        try {
            val response = organisationsDataSource.getAllRepos(organisation, perPage, page)
            emit(APIResponse.success(response))
        } catch (e: Exception) {
            emit(APIResponse.error(e))
        }
    }

    override suspend fun searchRepos(
        query: QueryModel,
        perPage: Int,
        page: Int
    ): Flow<APIResponse<SearchOrgModel>> = flow {
        emit(APIResponse.loading())
        try {
            val response = organisationsDataSource.searchRepos(query, perPage, page)
            emit(APIResponse.success(response))
        } catch (e: Exception) {
            emit(APIResponse.error(e))
        }
    }
}