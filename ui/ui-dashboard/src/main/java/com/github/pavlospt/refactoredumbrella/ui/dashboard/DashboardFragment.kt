package com.github.pavlospt.refactoredumbrella.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.GithubRepoItem
import com.github.pavlospt.refactoredumbrella.ui.design_system.RefactoredUmbrellaTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardFragment : Fragment() {

    private val dashboardViewModel by viewModel<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        setContent {
            RefactoredUmbrellaTheme {
                DashboardScreen(dashboardViewModel = dashboardViewModel)
            }
        }
    }
}

@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel) {
    val githubRepos by dashboardViewModel.githubRepos.observeAsState(initial = emptyList())
    GithubReposList(githubRepos = githubRepos)
}

@Composable
fun GithubReposList(githubRepos: List<GithubRepoItem>) {
    LazyColumnFor(items = githubRepos) { githubRepo ->
        GithubRepoUIItem(githubRepo = githubRepo)
        Divider(color = MaterialTheme.colors.primary)
    }
}

@Composable
fun GithubRepoUIItem(githubRepo: GithubRepoItem) {
    ListItem(
        text = { Text(text = githubRepo.name) },
        secondaryText = { Text(text = stringResource(R.string.repo_stars, githubRepo.stars)) }
    )
}
