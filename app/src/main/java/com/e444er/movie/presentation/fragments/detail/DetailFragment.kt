package com.e444er.movie.presentation.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.e444er.movie.R
import com.e444er.movie.common.Constants
import com.e444er.movie.databinding.DetailFragmentBinding
import com.e444er.movie.domain.model.Movie
import com.e444er.movie.presentation.fragments.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.e444er.movie.presentation.fragments.detail.DetailFragmentArgs as DetailFragmentArgs1

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding by viewBinding(DetailFragmentBinding::bind)
    private val args: DetailFragmentArgs1 by navArgs()
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiBind(args.movieId)

        viewModel.saveMovies.observe(viewLifecycleOwner) {
            binding.checkBox.apply {
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewModel.isClick(true)
                        viewModel.addMovies(args.movieId)
                    } else {
                        viewModel.isClick(false)
                        viewModel.deleteMovies(args.movieId)
                    }
                }
            }
        }


    }


    private fun uiBind(movieId: Movie) {
        binding.apply {
            Glide.with(root)
                .load(Constants.IMAGE_URL + movieId.posterPath)
                .error(R.drawable.ic_baseline_error_24)
                .into(imageView)

            textSearch.text = movieId.title
            textSearchDate.text = movieId.releaseDate
            textSearchRating.text = movieId.voteAverage.toString()
            textSummary.text = movieId.overview

        }

    }
}