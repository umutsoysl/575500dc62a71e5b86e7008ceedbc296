package com.umut.soysal.spacedelivery.core.state

sealed class ServiceResult<out T> {

    class Success<T>(val data: T) : ServiceResult<T>()

    class Error(val message: String) : ServiceResult<Nothing>()

    object Loading : ServiceResult<Nothing>()
}