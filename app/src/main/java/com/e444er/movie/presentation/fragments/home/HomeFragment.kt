package com.e444er.movie.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.e444er.movie.R
import com.e444er.movie.databinding.HomeFragmentBinding
import com.e444er.movie.presentation.fragments.home.toprating.TopRatingAdapter
import com.e444er.movie.presentation.fragments.home.topweek.TopWeekAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding by viewBinding(HomeFragmentBinding::bind)
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var _adapter: ListAdapter
    private lateinit var _topRatingAdapter: TopRatingAdapter
    private lateinit var _topWeekAdapter: TopWeekAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListLife()
        getTopRatingLife()
        getTopWeek()
        rvList()
    }

    private fun rvList() {
        binding.rvPopular.apply {
            _adapter = ListAdapter()
            adapter = _adapter
            setHasFixedSize(true)
        }

        binding.rvTopRating.apply {
            _topRatingAdapter = TopRatingAdapter()
            adapter = _topRatingAdapter
            setHasFixedSize(true)
        }

        binding.rvTopWeek.apply {
            _topWeekAdapter = TopWeekAdapter()
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
                    _topWeekAdapter.differ.submitList(it.toMutableList())
                }
            }
        }
    }

    private fun getListLife() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.trendingMovies.collect { it ->
                if (it.isLoading) {
                    binding.progressBar.isVisible = true
                    binding.rvPopular.isVisible = false
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.isVisible = false
                    binding.rvPopular.isVisible = false
                }
                it.data?.let {
                    binding.progressBar.isVisible = false
                    binding.rvPopular.isVisible = true
                    _adapter.differ.submitList(it.toMutableList())
                }
            }
        }
    }

    private fun getTopRatingLife() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.topMovies.collect { it ->
                if (it.isLoading) {
                    binding.progressBar.isVisible = true
                    binding.rvTopRating.isVisible = false
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.isVisible = false
                    binding.rvTopRating.isVisible = false
                }
                it.data?.let {
                    binding.progressBar.isVisible = false
                    binding.rvTopRating.isVisible = true
                    _topRatingAdapter.differ.submitList(it.toMutableList())
                }
            }
        }
    }

//    private fun setClick() {
//        _adapter.onClickListener = {
//            val nav = MainFragmentDirections.actionMainFragmentToMainDetailFragment(it)
//            findNavController().navigate(nav)
//        }
//    }
}
