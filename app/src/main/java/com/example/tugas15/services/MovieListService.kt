package com.example.tugas15.services

import com.example.tugas15.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieListService {
    @GET("movie/popular?api_key=af016fe4e15fb311411c83a16af9d6a7")
    fun getMoviesList(): Call<MovieResponse>
}