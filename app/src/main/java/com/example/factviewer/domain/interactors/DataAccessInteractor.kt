package com.example.factviewer.domain.interactors

import com.example.factviewer.data.adapters.DataAdapters
import com.example.factviewer.data.database.entity.LikeUnit
import com.example.factviewer.data.repository.DBAnimalRepository
import com.example.factviewer.data.repository.NetworkAnimalRepository
import com.example.factviewer.utils.Settings
import com.example.factviewer.utils.WorkMode
import com.example.factviewer.utils.events.Events
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class DataAccessInteractor  @Inject constructor() {
    @Inject
    lateinit var networkAnimalRepository: NetworkAnimalRepository

    @Inject
    lateinit var dbAnimalRepository: DBAnimalRepository

    /**
     * запрос списка фактов о животных
     * @param animal тип живонтоно
     * @param amount количество записей
     */
    fun getAnimalFacts(animal: String, amount: Int){
        println("get animal facts")

        GlobalScope.async {
            val likeList = dbAnimalRepository.getLikeList()
            println("get animal facts ")

            val res = when (Settings.currentWorkMode) {
                WorkMode.Online -> DataAdapters.factListToAnimalList(
                    networkAnimalRepository.getAllFromNet(animal, amount)
                )
                else -> DataAdapters.dbUnitListToAnimalFactList(dbAnimalRepository.getAllFromDatabase(animal, amount))
            }
            println("get animal facts ${res?.size}")

            res?.forEach {
                it.isLiked = likeList.contains(it.id)
            }
            if (Settings.currentWorkMode == WorkMode.Online)
                dbAnimalRepository.saveFactsToDatabase(DataAdapters.fromAnimalFactListToDbUnitList(res!!, animal))
            EventBus.getDefault().post(res?.let { Events.OnFactsListReceived(it) })
        }
    }

    /**
     * запрос избранных записей
     */
    fun getLikedFacts() {
        GlobalScope.async {
            val list =  dbAnimalRepository.getLikedFacts()
            EventBus.getDefault().post(Events.OnFactsListReceived(list))
        }
    }

    /**
     * получение записи но идентификатору
     * @param id уникальный идентификатор
     */
    fun getById(id: String){
        GlobalScope.async {
            val res =  when (Settings.currentWorkMode) {
                WorkMode.Online -> networkAnimalRepository.getById(id)
                else -> dbAnimalRepository.getFactFromDatabase(id)
            }
            EventBus.getDefault().post(Events.OnDetailsReceived(res, id))
        }
    }
    /**
     * Добавление или удаление статьи из избранного
     * @param id уникальный идентификатор статьи
     * @param isLiked - признак выбора
     */
    fun updateLike(id: String, isLiked: Boolean) {
        GlobalScope.async {
            dbAnimalRepository.updateLike(id, isLiked)
        }
    }
}