package com.example.foxpictures.utils

sealed class Resource<T> {

    class Default<T> : Resource<T>()

    class Successful<T>(val data: T) : Resource<T>()

    class Loading<T> : Resource<T>()

    class Error<T>(val message: String?) : Resource<T>()
}