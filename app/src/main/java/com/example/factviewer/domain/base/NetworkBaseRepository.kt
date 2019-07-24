package com.example.factviewer.domain.base

import com.example.factviewer.domain.network.RequestResult
import retrofit2.Response
import java.io.IOException

open class NetworkBaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : RequestResult<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is RequestResult.Success ->
                data = result.data
            is RequestResult.Error -> {
                println("1.DataRepository $errorMessage & Exception - ${result.exception}")
            }
        }
        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : RequestResult<T>{
        val response = call.invoke()
        if(response.isSuccessful) return RequestResult.Success(response.body()!!)

        return RequestResult.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}