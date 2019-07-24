package com.example.factviewer.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.factviewer.utils.ANIMALS_TAB_NAME

@Dao
interface AnimalFactsDAO{
    @Query("SELECT  *FROM $ANIMALS_TAB_NAME where animal = :animal")
    suspend fun getSavedCatsFacts(animal : String)  :List<AnimalFactUnit>

    @Query("SELECT fact FROM $ANIMALS_TAB_NAME where id = :id")
    suspend fun getFact(id : String) : String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimalFacts(facts : List<AnimalFactUnit>)
}