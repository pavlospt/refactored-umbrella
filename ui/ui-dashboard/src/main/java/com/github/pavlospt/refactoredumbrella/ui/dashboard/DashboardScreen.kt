package com.github.pavlospt.refactoredumbrella.ui.dashboard

import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource

@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel) {
    val githubRepos by dashboardViewModel.githubRepos.observeAsState(initial = emptyList())
    Scaffold(topBar = { TopAppBar(title = { Text("Dashboard") }) }) {
        GithubReposList(githubRepos = githubRepos)
    }
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
