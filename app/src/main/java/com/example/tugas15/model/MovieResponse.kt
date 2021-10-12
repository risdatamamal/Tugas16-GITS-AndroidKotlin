package com.example.tugas15.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieResponse(
    @field:SerializedName("results")
    val movies: List<Movies>,

) : Parcelable {
    constructor() : this(mutableListOf())
}

@Parcelize
data class Movies(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    ) : Parcelable