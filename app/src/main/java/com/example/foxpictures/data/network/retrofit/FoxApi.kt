package com.example.foxpictures.data.network.retrofit

import com.example.foxpictures.data.network.retrofit.model.FoxResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface FoxApi {
    @GET("floof/")
    suspend fun getPicture(): FoxResponse
}