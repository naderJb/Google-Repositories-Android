package com.nader.googlerepositoriesandroid.organizationrepositrories.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nader.googlerepositoriesandroid.base.model.DataStatus
import com.nader.googlerepositoriesandroid.base.model.SingleEvent
import com.nader.googlerepositoriesandroid.base.model.Status
import com.nader.googlerepositoriesandroid.base.utils.GlobalVars.THROTTLE_DELAY
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.QueryModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.domain.OrganisationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganisationsViewModel @Inject constructor(
    private val organisationsUseCase: OrganisationsUseCase
) : ViewModel() {
    val repositories = MutableLiveData<SingleEvent<List<OrganisationModel>>>()
    val searchRepositories = MutableLiveData<SingleEvent<List<OrganisationModel>>>()
    var status = MutableLiveData<SingleEvent<DataStatus>>()

    private var searchJob: Job? = null

    private var currentPage = 1

    fun getRepositories(
        organisation: String,
        perPage: Int = 10,
        refresh: Boolean = false
    ) {
        viewModelScope.launch {
            if (refresh) currentPage = 1 else currentPage.plus(1)
            organisationsUseCase.getAllRepos(organisation, perPage, currentPage)
                .collect { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            status.postValue(SingleEvent(DataStatus.DataLoaded))
                            response.data?.let {
                                repositories.postValue(SingleEvent((it)))
                            }
                        }

                        Status.LOADING -> {
                            status.postValue(SingleEvent(DataStatus.DataLoading))
                        }

                        Status.ERROR -> {
                            status.postValue(SingleEvent(DataStatus.DataError(response.exception)))
                        }
                    }
                }
        }
    }

    fun searchRepos(
        query: QueryModel,
        perPage: Int = 10,
        refresh: Boolean = false
    ) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(THROTTLE_DELAY)
            if (refresh) currentPage = 1 else currentPage.plus(1)
            organisationsUseCase.searchRepos(query, perPage, currentPage)
                .collect { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            status.postValue(SingleEvent(DataStatus.DataLoaded))
                            response.data?.items?.let {
                                searchRepositories.postValue(SingleEvent((it)))
                            }
                        }

                        Status.LOADING -> {
                            status.postValue(SingleEvent(DataStatus.DataLoading))
                        }

                        Status.ERROR -> {
                            status.postValue(SingleEvent(DataStatus.DataError(response.exception)))
                        }
                    }
                }
        }
    }
}