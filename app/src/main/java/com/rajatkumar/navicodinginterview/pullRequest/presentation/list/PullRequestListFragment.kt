package com.rajatkumar.navicodinginterview.pullRequest.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.rajatkumar.navicodinginterview.R
import com.rajatkumar.navicodinginterview.databinding.FragmentPullRequestListBinding
import com.rajatkumar.navicodinginterview.common.domain.BindingFragment
import com.rajatkumar.navicodinginterview.common.domain.Resource
import com.rajatkumar.navicodinginterview.common.domain.isNetworkAvailable
import com.rajatkumar.navicodinginterview.common.domain.navigateSafely
import com.rajatkumar.navicodinginterview.common.presentation.ErrorEvents
import com.rajatkumar.navicodinginterview.pullRequest.presentation.list.adapters.PRsAdapter
import com.rajatkumar.navicodinginterview.pullRequest.presentation.PullRequestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PullRequestListFragment : BindingFragment<FragmentPullRequestListBinding>() {

    private lateinit var adapter: PRsAdapter

    private val viewModel by viewModels<PullRequestViewModel>()

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentPullRequestListBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fireNetworkCall(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupListeners()

    }

    override fun onResume() {
        super.onResume()
        viewModel.forceRefresh()
    }

    private fun setupListeners() {
        adapter.setOnItemClickListener { pr, iv ->
            val extras = FragmentNavigatorExtras(iv to getString(R.string.transition_image))
            val directions =
                PullRequestListFragmentDirections.actionPullRequestListFragmentToPullRequestDetailFragment(
                    pullRequest = pr,
                )
            findNavController().navigateSafely(directions, extras)
        }
    }

    private fun setupObservers() {
        viewModel.allPullRequests.observe(viewLifecycleOwner) { prs ->
            when (val resource = prs.peekContent()) {
                is Resource.Error -> {
                    toggleLoading(false)
                    toggleErrorView(true)
                    binding.errorView.show(resource.error)
                }
                is Resource.Loading -> {
                    toggleLoading(true)
                }
                is Resource.Success -> {
                    toggleLoading(false)
                    resource.data?.pullRequests?.let { adapter.submitList(it) }
                }
            }
        }

        binding.errorView.subscribeEvents().observe(viewLifecycleOwner) { errorEvents ->
            when (errorEvents.peekContent()) {
                ErrorEvents.RetryClicked -> {
                    viewModel.fireNetworkCall(requireContext())
                    toggleErrorView(false)
                }
            }
        }
    }

    private fun toggleErrorView(toggle: Boolean) {
        binding.errorView.isVisible = toggle
    }

    private fun toggleLoading(toggle: Boolean) {
        binding.progressBar.isVisible = toggle
    }

    private fun setupRecyclerView() {
        binding.apply {
            adapter = PRsAdapter()
            rvPrs.adapter = adapter
        }
    }
}
