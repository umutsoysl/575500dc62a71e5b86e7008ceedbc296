package com.umut.soysal.spacedelivery.core.base

import com.umut.soysal.spacedelivery.core.state.ServiceResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

class BaseRepository {

    companion object {
        val api  = BaseRepository()
    }

    suspend fun <T> fetch(apiCall: suspend () -> Response<T>): Flow<ServiceResult<T>> = flow {
        emit(ServiceResult.Loading)

        val response: Response<T> = apiCall()

        if (response.isSuccessful) {
            emit(ServiceResult.Success(response.body()!!))
        }

    }.catch {
        when (it) {
            is java.io.IOException -> emit(ServiceResult.Error(it.message.toString()))
            is HttpException -> {
                emit(ServiceResult.Error(it.response()?.errorBody().toString()))
            }
            else -> {
                emit(ServiceResult.Error(it.message.toString()))
            }
        }
    }
}