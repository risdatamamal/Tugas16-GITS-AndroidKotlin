package com.example.tugas15.services

import com.example.tugas15.data.ModelMoviesDetail
import com.example.tugas15.data.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkConfig {

    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(Movies::class.java)
    fun getServieDetail() = getRetrofit().create(DetailMovies::class.java)
}

interface Movies {
    @GET("movie/popular?api_key=af016fe4e15fb311411c83a16af9d6a7")
    fun getMoviesList(): Call<MovieResponse>
}

interface DetailMovies {
    @GET("movie/{movie_id}?api_key=af016fe4e15fb311411c83a16af9d6a7&language=en-US")
    fun getDataDetail(): Call<ModelMoviesDetail>
}
