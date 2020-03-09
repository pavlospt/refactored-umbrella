package com.github.pavlospt.refactoredumbrella.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.pavlospt.refactoredumbrella.R
import com.github.pavlospt.refactoredumbrella.databinding.FragmentHomeBinding
import com.github.pavlospt.refactoredumbrella.ext.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })
    }
}
