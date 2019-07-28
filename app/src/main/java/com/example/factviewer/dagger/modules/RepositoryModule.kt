package com.example.factviewer.dagger.modules

import com.example.factviewer.data.network.AnimalFactsApi
import com.example.factviewer.data.database.AnimalFactsDatabase
import com.example.factviewer.data.database.dao.LikesDao
import com.example.factviewer.data.repository.DBAnimalRepository
import com.example.factviewer.data.repository.NetworkAnimalRepository
import com.example.factviewer.domain.interactors.DataAccessInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAnimalFactsDbRepository(
        currencyDatabase: AnimalFactsDatabase,
        api: AnimalFactsApi,
        likesDao: LikesDao
    ): DBAnimalRepository =
        DBAnimalRepository(currencyDatabase.animalFactsDAO(), api, likesDao)

    @Provides
    @Singleton
    fun provideAnimalFactsNetRepository(api: AnimalFactsApi): NetworkAnimalRepository =
        NetworkAnimalRepository(api)

    @Provides
    @Singleton
    fun provideInteractor(): DataAccessInteractor {
        return DataAccessInteractor()
    }
}