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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment:Fragment(R.layout.home_fragment) {

    private val binding by viewBinding(HomeFragmentBinding::bind)
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var _adapter: ListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPopular.apply {
            _adapter = ListAdapter()
            adapter = _adapter
            setHasFixedSize(true)
        }

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

//    private fun setClick() {
//        _adapter.onClickListener = {
//            val nav = MainFragmentDirections.actionMainFragmentToMainDetailFragment(it)
//            findNavController().navigate(nav)
//        }
//    }
}
