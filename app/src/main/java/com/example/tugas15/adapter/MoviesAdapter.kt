package com.example.tugas15.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tugas15.activity.MovieDetailActivity
import com.example.tugas15.R
import com.example.tugas15.model.Movies

class MoviesAdapter(private val listMovies: List<Movies>) : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val title_movies: TextView = itemView.findViewById(R.id.title_movies)
        val popularity_movies: TextView = itemView.findViewById(R.id.popularity_movies)
        val overview_movies: TextView = itemView.findViewById(R.id.overview_movies)
        val poster_path_movies: ImageView = itemView.findViewById(R.id.poster_path_movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val dataMovies = listMovies[position]

        Glide.with(holder.itemView).load(IMAGE_BASE + dataMovies.posterPath).apply(RequestOptions().override(55, 55)).into(holder.poster_path_movies)
        holder.title_movies.text = dataMovies.title
        holder.popularity_movies.text = dataMovies.popularity.toString()
        holder.overview_movies.text = dataMovies.overview
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java)
            intent.putExtra("id", dataMovies.id.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listMovies.size

    fun setData(data: List<Movies>) {
//        listMovies.clear()
//        listMovies.addAll(data)
        notifyDataSetChanged()
    }
}