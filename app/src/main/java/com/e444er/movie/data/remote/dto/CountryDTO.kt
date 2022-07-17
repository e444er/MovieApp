package com.e444er.movie.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("name")
    val name: String
)