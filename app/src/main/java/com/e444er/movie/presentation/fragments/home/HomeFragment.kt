package com.e444er.movie.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.e444er.movie.R
import com.e444er.movie.databinding.HomeFragmentBinding
import com.e444er.movie.presentation.fragments.home.toprating.TopRatingAdapter
import com.e444er.movie.presentation.fragments.home.topweek.TopWeekAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding by viewBinding(HomeFragmentBinding::bind)
    private val viewModel: MovieViewModel by viewModels()
    private val _adapter by lazy { ListAdapter() }
    private val _topRatingAdapter by lazy { TopRatingAdapter() }
    private val _topWeekAdapter by lazy { TopWeekAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topRatingPaging.collectLatest {
                    _topRatingAdapter.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listPaging.collectLatest {
                    _adapter.submitData(it)
                }
            }
        }

        getTopWeek()
        rvList()
        setClick()
    }

    private fun rvList() {
        binding.rvPopular.apply {
            adapter = _adapter
            setHasFixedSize(true)
        }

        binding.rvTopRating.apply {
            adapter = _topRatingAdapter
            setHasFixedSize(true)
        }

        binding.rvTopWeek.apply {
            adapter = _topWeekAdapter
            setHasFixedSize(true)
        }
    }

    private fun getTopWeek() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.topWeek.collect { it ->
                if (it.isLoading) {
                    binding.progressBar.isVisible = true
                    binding.rvTopWeek.isVisible = false
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.isVisible = false
                    binding.rvTopWeek.isVisible = false
                }
                it.data?.let {
                    binding.progressBar.isVisible = false
                    binding.rvTopWeek.isVisible = true
                    _topWeekAdapter.differ.submitList(it)
                }
            }
        }
    }

    private fun setClick() {
        _topWeekAdapter.onClickListener = {
            val nav = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(nav)
        }

        _adapter.onClickListener = {
            val nav = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(nav)
        }

        _topRatingAdapter.onClickListener = {
            val nav = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(nav)
        }

    }

}
