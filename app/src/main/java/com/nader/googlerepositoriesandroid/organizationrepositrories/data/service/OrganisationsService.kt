package com.nader.googlerepositoriesandroid.organizationrepositrories.data.service

import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.SearchOrgModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OrganisationsService {

    @GET("$PATH_ORG/{organisation}/$GET_REPOSITORY")
    suspend fun getAllRepos(
        @Path("organisation") organisation: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<List<OrganisationModel>>

    @GET(GET_SEARCH_REPOSITORY)
    suspend fun searchRepos(
        @Query(encoded = true, value = "q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<SearchOrgModel>


    companion object {
        const val PATH_ORG = "orgs"
        const val GET_REPOSITORY = "repos"
        const val GET_SEARCH_REPOSITORY = "search/repositories"
    }
}