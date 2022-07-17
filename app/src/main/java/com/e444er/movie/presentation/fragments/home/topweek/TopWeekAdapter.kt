package com.e444er.movie.presentation.fragments.home.topweek

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e444er.movie.R
import com.e444er.movie.common.Constants
import com.e444er.movie.databinding.TopWeekBinding
import com.e444er.movie.databinding.TopratingItemBinding
import com.e444er.movie.domain.model.Movie

class TopWeekAdapter : RecyclerView.Adapter<TopWeekAdapter.MyViewHolder>() {

    var onClickListener: ((Movie) -> Unit)? = null

    class MyViewHolder(val binding: TopWeekBinding) :
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
            TopWeekBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataId = differ.currentList[position]
        holder.binding.apply {
            movieTitle.text = dataId.title
            Glide.with(root)
                .load(Constants.IMAGE_URL + dataId.posterPath)
                .error(R.drawable.ic_baseline_error_24)
                .centerCrop()
                .into(imageHigh)
            Glide.with(root)
                .load(Constants.IMAGE_URL + dataId.posterPath)
                .error(R.drawable.ic_baseline_error_24)
                .centerCrop()
                .into(imageHighAlpha)
            dataTime.text = dataId.releaseDate
            summary.text = dataId.overview
            textRating.text = dataId.voteAverage.toString()

        }


        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(dataId)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
