package com.example.factviewer.api

import com.example.factviewer.domain.network.Fact
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimalFactsApi {
    @GET("/facts/random?")
    fun getRandomFactsAsync(@Query("animal_type") animal: String, @Query("amount") amount: Int)
            : Deferred<Response<List<Fact>>>

    @GET("/facts/{id}")
    fun getFactByIdAsync(@Path("id") id : String) : Deferred<Response<Fact>>
}