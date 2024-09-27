package com.example.foxpictures.domain.usecase

import com.example.foxpictures.data.repository.FoxPictureRepositoryImpl
import com.example.foxpictures.domain.model.FoxPicture
import com.example.foxpictures.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val foxPictureRepository: FoxPictureRepositoryImpl
) {
    suspend operator fun invoke(): Flow<Resource<FoxPicture>> = foxPictureRepository.getFoxPicture()
}