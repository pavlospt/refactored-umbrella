package com.github.pavlospt.refactoredumbrella.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.github.pavlospt.refactoredumbrella.R
import com.github.pavlospt.refactoredumbrella.databinding.FragmentHomeBinding
import com.github.pavlospt.refactoredumbrella.ext.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.view.clicks

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        private const val CLICK_DEBOUNCE = 400L
    }

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitRepo
            .clicks()
            .debounce(CLICK_DEBOUNCE)
            .map {
                HomeViewIntent.AddGithubRepo(
                    repoName = binding.repoName.text.toString(),
                    repoStars = binding.repoStars.text.toString().toInt()
                )
            }
            .onEach { homeViewModel.processIntent(intentHome = it) }
            .launchIn(lifecycleScope)

        homeViewModel.uiEvents.observe(viewLifecycleOwner, Observer {
            renderUIEvent(it)
        })
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
