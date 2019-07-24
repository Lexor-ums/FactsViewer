package com.example.factviewer.dagger.modules

import com.example.factviewer.api.AnimalFactsApi
import com.example.factviewer.domain.database.AnimalFactsDatabase
import com.example.factviewer.domain.repository.AnimalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAnimalFactsRepository(currencyDatabase: AnimalFactsDatabase, api : AnimalFactsApi): AnimalRepository =
        AnimalRepository(currencyDatabase.animalFactsDAO(), api)
}