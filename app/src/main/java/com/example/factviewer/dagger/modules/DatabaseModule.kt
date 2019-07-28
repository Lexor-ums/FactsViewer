package com.example.factviewer.dagger.modules

import android.app.Application
import androidx.room.Room
import com.example.factviewer.data.database.dao.AnimalFactsDAO
import com.example.factviewer.data.database.AnimalFactsDatabase
import com.example.factviewer.data.database.dao.LikesDao
import com.example.factviewer.utils.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAnimalFactsDataBase(application: Application): AnimalFactsDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AnimalFactsDatabase::class.java, DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimalFactsDAO(currencyDatabase: AnimalFactsDatabase): AnimalFactsDAO = currencyDatabase.animalFactsDAO()
    @Provides
    @Singleton
    fun provideLikesDAO(currencyDatabase: AnimalFactsDatabase): LikesDao = currencyDatabase.likesDAO()
}