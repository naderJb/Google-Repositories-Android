package com.nader.googlerepositoriesandroid.organizationrepositrories.di

import com.nader.googlerepositoriesandroid.organizationrepositrories.data.remote.OrganisationsDataSource
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.remote.OrganisationsDataSourceImpl
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.repo.OrganisationsRepo
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.repo.OrganisationsRepoImpl
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.service.OrganisationsService
import com.nader.googlerepositoriesandroid.organizationrepositrories.domain.OrganisationsUseCase
import com.nader.googlerepositoriesandroid.organizationrepositrories.domain.OrganisationsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class OrganisationsModule {

    @Provides
    fun provideOrganisationService(retrofit: Retrofit): OrganisationsService =
        retrofit.create(OrganisationsService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class OrganisationBindModule {
        @Binds
        abstract fun bindAOrganisationDataSource(
            organisationsDataSourceImpl: OrganisationsDataSourceImpl
        ): OrganisationsDataSource

        @Binds
        abstract fun bindAOrganisationRepository(
            organisationsRepoImpl: OrganisationsRepoImpl
        ): OrganisationsRepo

        @Binds
        abstract fun bindAOrganisationUseCase(
            organisationsUseCaseImpl: OrganisationsUseCaseImpl
        ): OrganisationsUseCase
    }
}