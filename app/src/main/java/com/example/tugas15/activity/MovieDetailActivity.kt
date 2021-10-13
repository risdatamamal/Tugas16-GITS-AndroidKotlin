package com.example.tugas15.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas15.R
import com.example.tugas15.model.MoviesDetailResponse
import com.example.tugas15.services.NetworkConfig
import kotlinx.android.synthetic.main.detail_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val id = intent.getStringExtra("id")
        getDetailMovie(id)
    }

    private fun getDetailMovie(id: String?) {
        NetworkConfig().getServiceDetailMovie()
            .getDetailMovie(id!!.toInt())
            .enqueue(object : Callback<MoviesDetailResponse> {
                override fun onResponse(
                    call: Call<MoviesDetailResponse>?,
                    response: Response<MoviesDetailResponse>?
                ) {
                    if (response!!.isSuccessful){
                        tv_title.text = response.body()?.originalTitle
                        tv_popularity.text = response.body()?.popularity.toString()
                        tv_overview.text = response.body()?.overview
                        showDetail(response.body())
                    }
                }

                override fun onFailure(call: Call<MoviesDetailResponse>?, t: Throwable?) {
                    Toast.makeText(this@MovieDetailActivity, "Response : $t", Toast.LENGTH_LONG).show()
                }
            })
    }

    fun showDetail(body: MoviesDetailResponse?) {
        tv_title.text = body?.originalTitle
        tv_popularity.text = body?.popularity.toString()
        tv_overview.text = body?.overview
    //        val poster = intent.getIntExtra(EXTRA_POSTER, 1)
    //        poster_path_movies.setImageResource(poster)
    }
}