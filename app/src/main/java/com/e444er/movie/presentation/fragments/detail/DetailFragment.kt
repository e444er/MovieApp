package com.e444er.movie.presentation.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.e444er.movie.R
import com.e444er.movie.common.Constants
import com.e444er.movie.databinding.DetailFragmentBinding
import com.e444er.movie.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import com.e444er.movie.presentation.fragments.detail.DetailFragmentArgs as DetailFragmentArgs1

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding by viewBinding(DetailFragmentBinding::bind)
    private val args: DetailFragmentArgs1 by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiBind(args.movieId)
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