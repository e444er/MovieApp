package com.e444er.movie.presentation.fragments.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.e444er.movie.R
import com.e444er.movie.databinding.FavoriteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.favorite_fragment) {

    private val binding by viewBinding(FavoriteFragmentBinding::bind)
    private val viewModel: FavoriteViewModel by viewModels()
    private val _adapter by lazy { FavoriteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        getTopWeek()
        rvList()
    }

    private fun getTopWeek() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.favoriteMovies.collect {
                _adapter.differ.submitList(it)
            }
        }
    }

    private fun rvList() {
        binding.recyclerview.apply {
            adapter = _adapter
            setHasFixedSize(true)
        }
    }
}