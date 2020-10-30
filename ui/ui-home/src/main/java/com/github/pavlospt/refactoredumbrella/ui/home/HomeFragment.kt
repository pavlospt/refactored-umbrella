package com.github.pavlospt.refactoredumbrella.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview
import com.github.pavlospt.refactoredumbrella.ui.design_system.RefactoredUmbrellaTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        setContent {
            RefactoredUmbrellaTheme {
                RepoInputs { repoName, repoStars ->
                    homeViewModel
                        .processIntent(
                            HomeViewIntent.AddGithubRepo(
                                repoName = repoName,
                                repoStars = repoStars
                            )
                        )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.uiEvents.observe(viewLifecycleOwner, { renderUIEvent(it) })
    }

    private fun renderUIEvent(homeUIEvent: HomeUIEvent) {
        when (homeUIEvent) {
            HomeUIEvent.RepoAdded -> Toast.makeText(
                requireContext(),
                "Repo added",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun RepoInputs(
    onRepoAdd: (String, Int) -> Unit
) {
    val repoName = remember { mutableStateOf(TextFieldValue("")) }
    val repoStars = remember { mutableStateOf(TextFieldValue("")) }

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
                    onRepoAdd(
                        repoName.value.text,
                        repoStars.value.text.toInt()
                    )
                    repoName.value = TextFieldValue("")
                    repoStars.value = TextFieldValue("")
                }
            ) {
                Text(text = "Add repo")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoInputsPreview() {
    RepoInputs(onRepoAdd = { _, _ -> })
}
