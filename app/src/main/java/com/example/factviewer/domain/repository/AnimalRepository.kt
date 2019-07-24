package com.example.factviewer.domain.repository

import androidx.room.Dao
import com.example.factviewer.api.AnimalFactsApi
import com.example.factviewer.domain.animalfact.AnimalFact
import com.example.factviewer.domain.base.NetworkBaseRepository
import com.example.factviewer.domain.database.AnimalFactUnit
import com.example.factviewer.domain.database.AnimalFactsDAO
import com.example.factviewer.domain.network.Fact
import com.example.factviewer.utils.Settings
import com.example.factviewer.utils.WorkMode
import javax.inject.Inject

class AnimalRepository @Inject constructor(private val dao: AnimalFactsDAO, private val api: AnimalFactsApi): NetworkBaseRepository(){
    suspend fun getAnimalFacts(animal: String, amount: Int): MutableList<AnimalFact> {
        return when(Settings.currentWorkMode) {
            WorkMode.Online -> getAllFromNet(animal, amount)
            else -> getAllFromDatabase(animal, amount)
        }
    }

    suspend fun getById(id : String) : String?{
        return when(Settings.currentWorkMode) {
            WorkMode.Online -> getFactFromNet(id)
            else -> getFactFromDatabase(id)
        }
    }

    suspend fun insertToDatabse(animal : String, list : List<Fact>){
        var unitList = mutableListOf<AnimalFactUnit>()
        for(it in list){
            unitList.add(AnimalFactUnit(it._id, animal, it.text))
        }
        dao.insertAnimalFacts(unitList)
    }
    private suspend fun getFactFromNet(id : String) : String?{
        val result = safeApiCall(
            call = { api.getFactByIdAsync(id = id).await() },
            errorMessage = "Can`t do request fon network"
        )
        return result?.text
    }

    private suspend fun getFactFromDatabase(id : String) :  String?{
        return dao.getFact(id)
    }

    private suspend fun getAllFromNet(animal: String, amount: Int) : MutableList<AnimalFact> {
        val list = mutableListOf<AnimalFact>()
        val requestResponse = safeApiCall(
            call = { api.getRandomFactsAsync(animal = animal, amount = amount).await() },
            errorMessage = "Can`t do request fon network"
        )
        if(requestResponse == null){
            Settings.currentWorkMode = WorkMode.Offline
            return getAllFromDatabase(animal, amount)
        }
        for (it in requestResponse!!) {
            list.add(AnimalFact("", it._id, it.text))
        }
        return list
    }

    private suspend fun getAllFromDatabase(animal: String, amount: Int) : MutableList<AnimalFact>{
        val list = mutableListOf<AnimalFact>()
        val request = dao.getSavedCatsFacts(animal)
        for (it in request){
            list.add(AnimalFact("",it.id, it.fact))
        }
        return list
    }
}