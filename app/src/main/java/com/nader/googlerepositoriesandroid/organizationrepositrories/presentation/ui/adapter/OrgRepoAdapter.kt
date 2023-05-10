package com.nader.googlerepositoriesandroid.organizationrepositrories.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nader.googlerepositoriesandroid.databinding.ItemOrganisationBinding
import com.nader.googlerepositoriesandroid.organizationrepositrories.data.model.OrganisationModel
import javax.inject.Inject

class OrgRepoAdapter @Inject constructor() :
    RecyclerView.Adapter<OrgRepoAdapter.OrgRepoViewHolder>() {

    private var repositories = ArrayList<OrganisationModel>()

    private var loadMoreListener: (() -> Unit)? = null

    private var onItemSelected: ((OrganisationModel) -> Unit)? = null

    fun setOnItemSelected(listener: (OrganisationModel) -> Unit) {
        onItemSelected = listener
    }

    fun addData(repos: List<OrganisationModel>, isRefresh: Boolean = false) {
        if (isRefresh) {
            repositories.clear()
            repositories.addAll(ArrayList(repos))
            notifyDataSetChanged()
        } else {
            repositories.addAll(ArrayList(repos))
            notifyItemRangeChanged(repositories.size, repos.size)
        }
    }

    fun addOnLoadMoreListener(listener: () -> Unit) {
        loadMoreListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrgRepoViewHolder =
        OrgRepoViewHolder(
            ItemOrganisationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: OrgRepoViewHolder, position: Int) {
        if (position == itemCount - 1)
            loadMoreListener?.invoke()
        holder.bind(repositories[position])
    }

    inner class OrgRepoViewHolder(
        private val binding: ItemOrganisationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(organisationModel: OrganisationModel) {
            binding.repo = organisationModel
            binding.root.setOnClickListener { onItemSelected?.invoke(organisationModel) }
        }
    }


}