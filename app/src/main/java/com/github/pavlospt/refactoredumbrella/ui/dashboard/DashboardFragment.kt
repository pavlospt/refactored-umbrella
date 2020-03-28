package com.github.pavlospt.refactoredumbrella.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.github.pavlospt.refactoredumbrella.R
import com.github.pavlospt.refactoredumbrella.android.core.viewbinding.viewBinding
import com.github.pavlospt.refactoredumbrella.databinding.FragmentDashboardBinding
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.GithubRepoAdapter
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.GithubRepoItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.swiperefreshlayout.refreshes

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashboardViewModel by viewModel<DashboardViewModel>()
    private val binding by viewBinding(FragmentDashboardBinding::bind)
    private val adapter = GithubRepoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reposList.adapter = adapter

        dashboardViewModel.githubRepos.observe(viewLifecycleOwner, Observer {
            displayGithubRepos(it)
        })

        binding.reposListPtr
            .refreshes()
            .map { DashboardViewIntent.Refresh }
            .onEach { dashboardViewModel.processIntent(intentDashboard = it) }
            .launchIn(lifecycleScope)
    }

    private fun displayGithubRepos(githubRepos: List<GithubRepoItem>) {
        adapter.submitList(githubRepos)
        binding.reposListPtr.isRefreshing = false
    }
}
