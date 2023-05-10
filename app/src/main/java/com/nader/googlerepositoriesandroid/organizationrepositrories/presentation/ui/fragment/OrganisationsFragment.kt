package com.nader.googlerepositoriesandroid.organizationrepositrories.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nader.googlerepositoriesandroid.base.extensions.onTextChanged
import com.nader.googlerepositoriesandroid.base.extensions.safe
import com.nader.googlerepositoriesandroid.base.extensions.shouldShowFap
import com.nader.googlerepositoriesandroid.base.extensions.showToast
import com.nader.googlerepositoriesandroid.base.model.DataStatus
import com.nader.googlerepositoriesandroid.base.model.OrgEnum
import com.nader.googlerepositoriesandroid.base.utils.Prefs
import com.nader.googlerepositoriesandroid.databinding.FragmentOrganisationsBinding
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.QueryModel
import com.nader.googlerepositoriesandroid.organizationrepositrories.presentation.ui.adapter.OrgRepoAdapter
import com.nader.googlerepositoriesandroid.organizationrepositrories.presentation.viewmodel.OrganisationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OrganisationsFragment : Fragment() {
    private var _binding: FragmentOrganisationsBinding? = null
    private val binding get() = _binding!!

    private val organisationViewModel: OrganisationsViewModel by viewModels()

    private val prefs: Prefs by lazy { Prefs(requireActivity()) }

    @Inject
    lateinit var orgRepoAdapter: OrgRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrganisationsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        implementListeners()
        initObservers()
    }

    private fun initViews() {
        binding.rvRepo.adapter = orgRepoAdapter
        organisationViewModel.getRepositories(OrgEnum.GOOGLE.org)
        AppCompatDelegate.setDefaultNightMode(prefs.getTheme())
    }

    private fun implementListeners() {
        binding.tvSwitch.setOnClickListener {
            val nightMode = when (AppCompatDelegate.getDefaultNightMode()) {
                AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_YES
            }
            AppCompatDelegate.setDefaultNightMode(nightMode)
            prefs.setTheme(nightMode)
        }
        orgRepoAdapter.addOnLoadMoreListener {
            organisationViewModel.getRepositories(OrgEnum.GOOGLE.org)
        }
        orgRepoAdapter.setOnItemSelected {
            findNavController().navigate(
                OrganisationsFragmentDirections.actionOrganisationsFragmentToRepositoryDetailFragment(
                    it
                )
            )
        }
        binding.rvRepo.shouldShowFap {
            binding.fap.isVisible = it
        }
        binding.fap.setOnClickListener {
            binding.rvRepo.smoothScrollToPosition(0)
        }
        binding.svSearch.onTextChanged { text ->
            text?.let {
                organisationViewModel.searchRepos(
                    QueryModel(query = text),
                    refresh = true
                )
            } ?: kotlin.run {
                organisationViewModel.getRepositories(OrgEnum.GOOGLE.org, refresh = true)
            }
        }

    }

    private fun initObservers() {
        organisationViewModel.repositories.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { repos ->
                orgRepoAdapter.addData(repos)
            }
        }
        organisationViewModel.searchRepositories.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { repos ->
                orgRepoAdapter.addData(repos, true)
            }
        }
        organisationViewModel.status.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { status ->
                when (status) {
                    DataStatus.DataLoading -> showLoading(true)

                    DataStatus.DataLoaded -> showLoading(false)

                    is DataStatus.DataError -> {
                        showLoading(false)
                        requireContext().showToast(status.exception?.message.safe())
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.animationView.isVisible = isLoading
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}