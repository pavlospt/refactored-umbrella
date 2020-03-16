package com.github.pavlospt.refactoredumbrella.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.pavlospt.refactoredumbrella.R
import com.github.pavlospt.refactoredumbrella.databinding.FragmentDashboardBinding
import com.github.pavlospt.refactoredumbrella.ext.viewBinding
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.GithubRepoAdapter
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.GithubRepoItem
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    }

    private fun displayGithubRepos(githubRepos: List<GithubRepoItem>) {
        adapter.submitList(githubRepos)
    }
}
