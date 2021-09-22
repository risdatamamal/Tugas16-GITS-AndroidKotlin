package com.example.tugas15.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tugas15.R
import com.example.tugas15.data.PopularMovies
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(private val listMovies: List<PopularMovies>) : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindMovie(get: PopularMovies) {
            itemView.title_movies.text = get.title
            itemView.popularity_movies.text = get.popularity.toString()
            itemView.overview_movies.text = get.overview
            Glide.with(itemView).load(IMAGE_BASE + get.posterPath).apply(RequestOptions().override(55, 55)).into(itemView.poster_path_movies)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindMovie(listMovies.get(position))
    }

    override fun getItemCount(): Int = listMovies.size
}