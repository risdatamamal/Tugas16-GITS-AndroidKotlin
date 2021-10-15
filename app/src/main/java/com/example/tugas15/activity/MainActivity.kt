package com.example.tugas15.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas15.R
import com.example.tugas15.adapter.MoviesAdapter
import com.example.tugas15.model.MovieResponse
import com.example.tugas15.model.Movies
import com.example.tugas15.services.NetworkConfig
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val listmovieAdapter = MoviesAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Tugas 24"

        rv_movies.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_movies.adapter = listmovieAdapter

        getDataListMovie()
    }

    private fun getDataListMovie() {

        // List Movies
        NetworkConfig.getServiceMovieList()
            .getMoviesList()
            .enqueue(object : Callback<MovieResponse> {

                // Jika API listmovie dapat diterima
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        viewListMovie(response.body()!!)
                        val movie = response.body()!!.movies
                        for (item in movie) {
                            detailMovie(item.id)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Response Gagal", Toast.LENGTH_LONG).show()
                    }
                }

                // Jika API listmovie gagal diterima
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Reponse Gagal : $t", Toast.LENGTH_LONG).show()
                }
            })
    }

    fun viewListMovie(data: MovieResponse) {
        val movie = data.movies
        listmovieAdapter.setData(movie as List<Movies>)
    }

    fun detailMovie(id: Int?){
        NetworkConfig
    }
}