package com.example.factviewer.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.factviewer.data.database.entity.LikeUnit
import com.example.factviewer.utils.ANIMALS_TAB_LIKE

@Dao
interface LikesDao{
    @Insert
    fun addLike(unit: LikeUnit)

    @Query("SELECT *  FROM $ANIMALS_TAB_LIKE")
    suspend fun getLikes() : List<LikeUnit>

    @Query("DELETE FROM $ANIMALS_TAB_LIKE where id = :id")
    suspend fun removeLike(id : String)


}