package com.example.tugas15.services

import com.example.tugas15.model.MovieResponse
import com.example.tugas15.model.MoviesDetailResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkConfig {

    // set interceptor
    private fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceMovieList(): MovieListService = getRetrofit().create(MovieListService::class.java)
    fun getServiceDetailMovie(): MovieDetailService = getRetrofit().create(MovieDetailService::class.java)
}

interface MovieListService {
    @GET("movie/popular?api_key=af016fe4e15fb311411c83a16af9d6a7")
    fun getMoviesList(): Call<MovieResponse>
}

interface MovieDetailService {
    @GET("movie/{movie_id}?api_key=af016fe4e15fb311411c83a16af9d6a7&language=en-US")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Int): Call<MoviesDetailResponse>
}
