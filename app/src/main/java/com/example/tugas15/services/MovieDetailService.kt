package com.example.tugas15.services

import com.example.tugas15.model.MoviesDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {
    @GET("movie/{movie_id}?api_key=af016fe4e15fb311411c83a16af9d6a7&language=en-US")
    fun getDetailMovie(@Path("movie_id") id: Int): Call<MoviesDetailResponse>
}