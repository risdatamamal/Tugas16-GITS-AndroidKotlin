package com.example.tugas15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas15.adapter.MoviesAdapter
import com.example.tugas15.data.MovieResponse
import com.example.tugas15.services.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rv_movies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Tugas 15"
        rv_movies = findViewById(R.id.rv_movies)
        rv_movies.setHasFixedSize(true)

        NetworkConfig().getService()
            .getMoviesList()
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    rv_movies.adapter = MoviesAdapter(response.body()!!.movies)
                }
            })
    }
}