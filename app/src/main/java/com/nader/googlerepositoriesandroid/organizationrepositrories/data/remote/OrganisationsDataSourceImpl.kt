package com.nader.googlerepositoriesandroid.organizationrepositrories.data.remote

import com.nader.googlerepositoriesandroid.base.extensions.dataOrException
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.QueryModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.SearchOrgModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.service.OrganisationsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrganisationsDataSourceImpl @Inject constructor(
    private val organisationsService: OrganisationsService
) : OrganisationsDataSource {
    override suspend fun getAllRepos(
        organisation: String,
        perPage: Int,
        page: Int
    ): List<OrganisationModel> =
        withContext(Dispatchers.IO) {
            try {
                organisationsService.getAllRepos(organisation, perPage, page)
                    .dataOrException("Cannot get repositories")
            } catch (e: Exception) {
                throw e
            }
        }

    override suspend fun searchRepos(query: QueryModel, perPage: Int, page: Int): SearchOrgModel =
        withContext(Dispatchers.IO) {
            try {
                organisationsService.searchRepos(query.toQuery(), perPage, page)
                    .dataOrException("Cannot get repositories")
            } catch (e: Exception) {
                throw e
            }
        }


}