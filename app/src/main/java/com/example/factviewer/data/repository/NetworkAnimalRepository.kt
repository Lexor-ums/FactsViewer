package com.example.factviewer.data.repository

import com.example.factviewer.data.application.AnimalFact
import com.example.factviewer.data.database.entity.AnimalFactUnit
import com.example.factviewer.data.database.entity.LikeUnit
import com.example.factviewer.data.network.AnimalFactsApi
import com.example.factviewer.data.network.Fact
import com.example.factviewer.data.repository.base.NetworkBaseRepository
import com.example.factviewer.utils.Settings
import com.example.factviewer.utils.WorkMode
import javax.inject.Inject

class NetworkAnimalRepository @Inject constructor(private val api: AnimalFactsApi) : NetworkBaseRepository() {
    /**
     * запрос списка фактов о животных из сети
     * @param animal тип живонтоно
     * @param amount количество записей
     */
    suspend fun getAllFromNet(animal: String, amount: Int): List<Fact>? {
        println("getAllFromNet()")
        val requestResponse = safeApiCall(
            call = { api.getRandomFactsAsync(animal = animal, amount = amount).await() },
            errorMessage = "Can`t do request fon network"
        )
        if (requestResponse == null) {
            Settings.currentWorkMode = WorkMode.Offline
            return null
        }
        return requestResponse
    }
    /**
     * загрузка статьи из сети
     * @param id - уникальный идентификатор статьи
     */
    private suspend fun getFactFromNet(id: String): String? {
        val result = safeApiCall(
            call = { api.getFactByIdAsync(id = id).await() },
            errorMessage = "Can`t do request fon network"
        )
        return result?.text
    }
    suspend fun getById(id: String): String? {
            return getFactFromNet(id)
    }
}