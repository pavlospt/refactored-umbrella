package com.github.pavlospt.refactoredumbrella.ui.dashboard

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.pavlospt.refactoredumbrella.android.core.viewbinding.viewBinding
import com.github.pavlospt.refactoredumbrella.ui.dashboard.databinding.FragmentPOCBinding

class POCFragment : Fragment(R.layout.fragment_p_o_c) {

    private val binding by viewBinding(FragmentPOCBinding::bind)
    private val hAdapter = HAdapter()
    private val vAdapter = VAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hAdapter.submitList(
            (1..10).map {
                FooHItem(title = "$it")
            }
        )

        vAdapter.submitList(
            (10 downTo 1).map {
                FooVItem(title = "$it")
            }
        )

        binding.itemsList.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsList.adapter = vAdapter

        binding.swapAdaptersButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.root)

            binding.itemsList.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )

            binding.itemsList.adapter = hAdapter
        }
    }
}
