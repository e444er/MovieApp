package com.e444er.movie.presentation.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.e444er.movie.R
import com.e444er.movie.common.textChangeFlow
import com.e444er.movie.databinding.SearchFragmentBinding
import com.e444er.movie.presentation.fragments.home.HomeFragmentDirections
import com.e444er.movie.presentation.fragments.home.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val viewModel: SearchViewModel by viewModels()
    private val _adapter by lazy { SearchAdapter() }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSearch()
        rvList()
        binding.editSearch.textChangeFlow()
            .debounce(300)
            .distinctUntilChanged()
            .mapLatest { viewModel.searchName(it) }
            .flowOn(Dispatchers.IO)
            .catch { Toast.makeText(requireContext(), "Search", Toast.LENGTH_LONG).show() }
            .onEach { Log.d("onEach", "onEach is : $it") }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        setClick()
    }

    private fun rvList() {
        binding.rvSearch.apply {
            layoutManager = StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = _adapter
            setHasFixedSize(true)
        }
    }

    private fun getSearch() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.searchMovie.collect { it ->
                if (it.isLoading) {
                    binding.progressBar2.isVisible = true
                    binding.rvSearch.isVisible = false
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar2.isVisible = false
                    binding.rvSearch.isVisible = false
                }
                it.data?.let {
                    binding.progressBar2.isVisible = false
                    binding.rvSearch.isVisible = true
                    _adapter.differ.submitList(it)
                }
            }
        }
    }
    private fun setClick() {
        _adapter.onClickListener = {
            val nav = SearchFragmentDirections.actionSearchFragmentToDetailFragment2(it)
            findNavController().navigate(nav)
        }
    }


}