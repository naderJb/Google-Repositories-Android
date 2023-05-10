package com.nader.googlerepositoriesandroid.repositorydetails.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nader.googlerepositoriesandroid.R
import com.nader.googlerepositoriesandroid.base.extensions.PaletteCreator
import com.nader.googlerepositoriesandroid.base.extensions.safe
import com.nader.googlerepositoriesandroid.base.utils.DateUtils
import com.nader.googlerepositoriesandroid.databinding.FragmentRepositoryDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RepositoryDetailFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding get() = _binding!!

    private val args: RepositoryDetailFragmentArgs by navArgs()

    override fun getTheme(): Int = R.style.TransparentBottomSheetDialogTheme
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            Glide
                .with(requireContext())
                .load(args.repository.ownerModel?.avatarUrl)
                .placeholder(R.drawable.ic_github)
                .error(R.drawable.ic_github)
                .fallback(R.drawable.ic_github)
                .listener(PaletteCreator {
//                it.lightMutedSwatch?.rgb?.let { color ->
//                    binding.root.setBackgroundColor(color)
//                }
                })
                .into(tvAvatar)
            tvRepoDate.text =
                getString(
                    R.string.repo_details_date,
                    DateUtils.formatDate(args.repository.updatedAt.safe())
                )
            tvRepoName.text = args.repository.fullName.safe()
            tvStargazerCount.text =
                getString(R.string.repo_details_stargazer, args.repository.stargazersCount.safe())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}