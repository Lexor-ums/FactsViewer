package com.example.factviewer.domain.database

import androidx.room.*
import com.example.factviewer.domain.animalfact.AnimalFact
import com.example.factviewer.utils.ANIMALS_TAB_NAME

@Dao
interface AnimalFactsDAO{
    @Query("SELECT  *FROM $ANIMALS_TAB_NAME where animal = :animal")
    suspend fun getSavedCatsFacts(animal : String)  :List<AnimalFactUnit>

    @Query("SELECT fact FROM $ANIMALS_TAB_NAME where id = :id")
    suspend fun getFact(id : String) : String

    @Query("SELECT * FROM $ANIMALS_TAB_NAME where id = :id")
    suspend fun getRecord(id : String) : AnimalFactUnit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimalFacts(facts : List<AnimalFactUnit>)
}