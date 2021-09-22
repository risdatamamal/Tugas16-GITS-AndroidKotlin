package com.example.tugas15.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieResponse(
    @field:SerializedName("results")
    val movies: List<PopularMovies>,

) : Parcelable {
    constructor() : this(mutableListOf())
}