package com.example.factviewer.data.repository

import com.example.factviewer.data.network.AnimalFactsApi
import com.example.factviewer.data.application.AnimalFact
import com.example.factviewer.data.repository.base.NetworkBaseRepository
import com.example.factviewer.data.database.entity.AnimalFactUnit
import com.example.factviewer.data.database.dao.AnimalFactsDAO
import com.example.factviewer.data.database.entity.LikeUnit
import com.example.factviewer.data.database.dao.LikesDao
import com.example.factviewer.utils.Settings
import com.example.factviewer.utils.WorkMode
import javax.inject.Inject

/**
 * методы получения данных
 */
class DBAnimalRepository @Inject constructor(
    private val dao: AnimalFactsDAO,
    private val api: AnimalFactsApi,
    private val likesDao: LikesDao
) :
    NetworkBaseRepository() {
  /**
     * запрос избранных записей
     */
    suspend fun getLikedFacts(): MutableList<AnimalFact> {
        var list = mutableListOf<AnimalFact>()
        val likeList = getLikeList()
        for (it in likeList) {
            val fact = dao.getRecord(it)
            list.add(AnimalFact("", it, fact.fact, true))
        }
        return list
    }

     /**
     * Добавление или удаление статьи из избранного
     * @param id уникальный идентификатор статьи
     * @param isLiked - признак выбора
     */
    suspend fun updateLike(id: String, isLiked: Boolean) {
        when (isLiked) {
            true -> likesDao.addLike(LikeUnit(id))
            false -> likesDao.removeLike(id)
        }
    }

    /**
     * запрос списка идентификаторов избранных статей
     */
    suspend fun getLikeList(): List<String> {
        println("get like list")
        var likeList = mutableListOf<String>()
        for (it in likesDao.getLikes()) {
            println("get like kist")
            likeList.add(it.id)
        }
        println("get like kist finish")
        return likeList
    }

    /**
     * загрузка статьи из базы данных
     * @param id - уникальный идентификатор статьи
     */
    suspend fun getFactFromDatabase(id: String): String? {
        return dao.getFact(id)
    }

    /**
     * запрос списка фактов о животных из базы данных
     * @param animal тип живонтоно
     * @param amount количество записей
     */
    suspend fun getAllFromDatabase(animal: String, amount: Int): List<AnimalFactUnit>? {
        println("getAllFromDatabase")
        return dao.getSavedCatsFacts(animal)
    }

    suspend fun saveFactsToDatabase(facts : List<AnimalFactUnit>?){
        if (facts != null) {
            dao.insertAnimalFacts(facts)
        }
    }
}