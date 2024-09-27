package com.example.foxpictures.data.repository

import com.example.foxpictures.data.network.retrofit.FoxApi
import com.example.foxpictures.data.network.retrofit.model.toDomain
import com.example.foxpictures.domain.model.FoxPicture
import com.example.foxpictures.domain.repository.FoxPictureRepository
import com.example.foxpictures.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FoxPictureRepositoryImpl @Inject constructor(
    private val api: FoxApi
) : FoxPictureRepository {
    override suspend fun getFoxPicture(): Flow<Resource<FoxPicture>> = flow {
        try {
            emit(Resource.Loading())
            val response = api.getPicture().toDomain()
            emit(Resource.Successful(data = response))
        } catch (e: Throwable) {
            emit(Resource.Error(message = e.message))
        }
    }
}