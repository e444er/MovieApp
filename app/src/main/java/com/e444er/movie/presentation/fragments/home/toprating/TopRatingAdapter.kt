package com.e444er.movie.presentation.fragments.home.toprating

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e444er.movie.R
import com.e444er.movie.common.Constants
import com.e444er.movie.databinding.MovieItemBinding
import com.e444er.movie.databinding.TopratingItemBinding
import com.e444er.movie.domain.model.Movie

class TopRatingAdapter : RecyclerView.Adapter<TopRatingAdapter.MyViewHolder>() {

    var onClickListener: ((Movie) -> Unit)? = null

    class MyViewHolder(val binding: TopratingItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private class DifferCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer<Movie>(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            TopratingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataId = differ.currentList[position]
        holder.binding.apply {
            textPopularTitle.text = dataId.title
            Glide.with(root)
                .load(Constants.IMAGE_URL + dataId.posterPath)
                .error(R.drawable.ic_baseline_error_24)
                .centerCrop()
                .into(imagePopular)
            textPopularYear.text = dataId.releaseDate
            textPopularRating.text = dataId.voteAverage.toString()
        }


        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(dataId)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}