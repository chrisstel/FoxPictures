package com.example.foxpictures.domain.repository

import com.example.foxpictures.domain.model.FoxPicture
import com.example.foxpictures.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FoxPictureRepository {
    suspend fun getFoxPicture(): Flow<Resource<FoxPicture>>
}