package com.example.factviewer.domain.repository

import androidx.room.Dao
import com.example.factviewer.api.AnimalFactsApi
import com.example.factviewer.domain.animalfact.AnimalFact
import com.example.factviewer.domain.base.NetworkBaseRepository
import com.example.factviewer.domain.database.AnimalFactUnit
import com.example.factviewer.domain.database.AnimalFactsDAO
import com.example.factviewer.domain.database.LikeUnit
import com.example.factviewer.domain.database.LikesDao
import com.example.factviewer.domain.network.Fact
import com.example.factviewer.utils.Settings
import com.example.factviewer.utils.WorkMode
import javax.inject.Inject

class AnimalRepository @Inject constructor(
    private val dao: AnimalFactsDAO,
    private val api: AnimalFactsApi,
    private val likesDao: LikesDao
) :
    NetworkBaseRepository() {
    suspend fun getAnimalFacts(animal: String, amount: Int): MutableList<AnimalFact> {
        return when (Settings.currentWorkMode) {
            WorkMode.Online -> getAllFromNet(animal, amount)
            else -> getAllFromDatabase(animal, amount)
        }
    }

    suspend fun getLikedFacts() : MutableList<AnimalFact>{
        var list = mutableListOf<AnimalFact>()
        val likeList = getLikeList()
        for(it in likeList){
            val fact = dao.getRecord(it)
            list.add(AnimalFact("", it, fact.fact, true))
        }
        return list
    }

    suspend fun getById(id: String): String? {
        return when (Settings.currentWorkMode) {
            WorkMode.Online -> getFactFromNet(id)
            else -> getFactFromDatabase(id)
        }
    }

    suspend fun updateLike(id: String, isLiked: Boolean) {
        when (isLiked) {
            true -> likesDao.addLike(LikeUnit(id))
            false -> likesDao.removeLike(id)
        }
    }

    suspend fun getLikeList() : List<String>{
        var likeList = mutableListOf<String>()
        for(it in likesDao.getLikes())
            likeList.add(it.id)
        return likeList
    }

    private suspend fun getFactFromNet(id: String): String? {
        val result = safeApiCall(
            call = { api.getFactByIdAsync(id = id).await() },
            errorMessage = "Can`t do request fon network"
        )
        return result?.text
    }

    private suspend fun getFactFromDatabase(id: String): String? {
        return dao.getFact(id)
    }

    private suspend fun getAllFromNet(animal: String, amount: Int): MutableList<AnimalFact> {
        val list = mutableListOf<AnimalFact>()
        val listOfUnits = mutableListOf<AnimalFactUnit>()
        val requestResponse = safeApiCall(
            call = { api.getRandomFactsAsync(animal = animal, amount = amount).await() },
            errorMessage = "Can`t do request fon network"
        )
        if (requestResponse == null) {
            Settings.currentWorkMode = WorkMode.Offline
            return getAllFromDatabase(animal, amount)
        }
        val likeList = getLikeList()
        for (it in requestResponse) {
            var isLiked = likeList.contains(it._id)
            list.add(AnimalFact("", it._id, it.text, isLiked))
            listOfUnits.add(AnimalFactUnit(it._id, it.type, it.text))
        }
        dao.insertAnimalFacts(listOfUnits)
        return list
    }

    private suspend fun getAllFromDatabase(animal: String, amount: Int): MutableList<AnimalFact> {
        val list = mutableListOf<AnimalFact>()
        val request = dao.getSavedCatsFacts(animal)
        val likeList = getLikeList()
        for (it in request) {
            var isLiked = likeList.contains(it.id)
            list.add(AnimalFact("", it.id, it.fact, isLiked))
        }
        return list
    }
}