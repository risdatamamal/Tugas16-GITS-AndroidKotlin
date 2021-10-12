package com.example.tugas15.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas15.R
import com.example.tugas15.adapter.MoviesAdapter
import com.example.tugas15.model.MoviesDetailResponse
import com.example.tugas15.model.MovieResponse
import com.example.tugas15.model.Movies
import com.example.tugas15.services.NetworkConfig
import kotlinx.android.synthetic.main.item_movie.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rv_movies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Tugas 24"
        rv_movies = findViewById(R.id.rv_movies)
        rv_movies.setHasFixedSize(true)

        // List Movies
        NetworkConfig().getServiceMovieList()
            .getMoviesList()
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    rv_movies.layoutManager = LinearLayoutManager(this@MainActivity)
                    val listMoviesAdapter = MoviesAdapter(response.body()!!.movies)
                    rv_movies.adapter = listMoviesAdapter
                    listMoviesAdapter.onItemClickCallback = 
                        object : MoviesAdapter.OnItemClickCallback {
                            override fun onItemClicked(data: Movies?) {
                                data?.let { showSelectedMovies(it) }
                            }
                        }
                }
            })
    }

    // Detail Movie
    private fun showSelectedMovies(movies: Movies) {
        NetworkConfig().getServiceDetailMovie()
            .getDetailMovie(0)
            .enqueue(object : Callback<MoviesDetailResponse> {
                override fun onFailure(call: Call<MoviesDetailResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MoviesDetailResponse>,
                    response: Response<MoviesDetailResponse>
                ) {
                    Toast.makeText(this@MainActivity, "Kamu memilih " + movies.title, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TITLE, R.id.tv_title)
                    intent.putExtra(DetailActivity.EXTRA_POPULARITY, R.id.tv_popularity)
                    intent.putExtra(DetailActivity.EXTRA_OVERVIEW, R.id.tv_overview)
                    intent.putExtra(DetailActivity.EXTRA_POSTER, R.id.poster_path_movies)
                    startActivity(intent)
                }
            })
    }

}