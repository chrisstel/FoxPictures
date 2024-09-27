package com.example.foxpictures.data.network.retrofit.model

import com.example.foxpictures.domain.model.FoxPicture
import com.google.gson.annotations.SerializedName

data class FoxResponse(
    @SerializedName("image")
    val image: String
)

fun FoxResponse.toDomain() = FoxPicture(
    url = image
)