package com.github.pavlospt.refactoredumbrella.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.pavlospt.refactoredumbrella.R
import com.github.pavlospt.refactoredumbrella.databinding.FragmentDashboardBinding
import com.github.pavlospt.refactoredumbrella.ext.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashboardViewModel by viewModel<DashboardViewModel>()
    private val binding by viewBinding(FragmentDashboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it
        })
    }
}
