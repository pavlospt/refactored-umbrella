package com.github.pavlospt.refactoredumbrella.ui.home

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedTask
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import com.github.pavlospt.refactoredumbrella.navigation.NavControllerAmbient
import com.github.pavlospt.refactoredumbrella.navigation.NavRoute
import com.github.pavlospt.refactoredumbrella.navigation.ScaffoldStateAmbient

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToDashboard: Boolean = false
) {
    val navController = NavControllerAmbient.current
    val scaffoldState = ScaffoldStateAmbient.current
    val repoName = remember { mutableStateOf(TextFieldValue("")) }
    val repoStars = remember { mutableStateOf(TextFieldValue("")) }
    val uiEvents by homeViewModel.uiEvents.observeAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) {
        Column(verticalArrangement = Arrangement.Top) {
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                OutlinedTextField(
                    value = repoName.value,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Text,
                    label = { Text(text = "Repo name") },
                    onValueChange = { newValue -> repoName.value = newValue }
                )
            }
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                OutlinedTextField(
                    value = repoStars.value,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Number,
                    label = { Text(text = "Repo stars") },
                    onValueChange = { newValue -> repoStars.value = newValue }
                )
            }
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        homeViewModel.processIntent(
                            intentHome = HomeViewIntent.AddGithubRepo(
                                repoName = repoName.value.text,
                                repoStars = repoStars.value.text.toInt()
                            )
                        )
                        repoName.value = TextFieldValue("")
                        repoStars.value = TextFieldValue("")

                        if (navigateToDashboard) {
                            navController.navigate(NavRoute.Dashboard.route)
                        }
                    }
                ) {
                    Text(text = "Add repo")
                }
            }
            when (uiEvents) {
                HomeUIEvent.RepoAdded ->
                    SuccessSnackbar(snackbarHostState = scaffoldState.snackbarHostState)
                else -> Unit
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SuccessSnackbar(snackbarHostState: SnackbarHostState) {
    val message = stringResource(id = R.string.repo_added_feedback_text)
    LaunchedTask { snackbarHostState.showSnackbar(message = message) }
}
