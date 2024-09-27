package com.example.foxpictures.ui.mainfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foxpictures.domain.model.FoxPicture
import com.example.foxpictures.domain.usecase.GetImageUseCase
import com.example.foxpictures.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {
    private val _currentImage = MutableStateFlow<Resource<FoxPicture>>(Resource.Default())
    val currentImage = _currentImage.asStateFlow()

    fun getFoxPicture() = viewModelScope.launch {
        getImageUseCase().collect { newFoxImage ->
            _currentImage.value = newFoxImage
        }
    }
}