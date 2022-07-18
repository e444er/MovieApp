package com.e444er.movie.presentation.fragments.home.toprating

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e444er.movie.R
import com.e444er.movie.common.Constants
import com.e444er.movie.databinding.MovieItemBinding
import com.e444er.movie.domain.model.Movie

class TopRatingAdapter : PagingDataAdapter<Movie, TopRatingAdapter.MyViewHolder>(ARTICLE_DIFF_CALLBACK) {

    class MyViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataId = getItem(position)
        holder.binding.apply {
            textPopularTitle.text = dataId?.title
            Glide.with(root)
                .load(Constants.IMAGE_URL + dataId?.posterPath)
                .error(R.drawable.ic_baseline_error_24)
                .centerCrop()
                .into(imagePopular)
            textPopularYear.text = dataId?.releaseDate
            textPopularRating.text = dataId?.voteAverage.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


}