package com.example.factviewer.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.factviewer.utils.ANIMALS_TAB_LIKE
import java.util.*

@Dao
interface LikesDao{
    @Insert
    fun addLike(unit: LikeUnit)

    @Query("SELECT *  FROM $ANIMALS_TAB_LIKE")
    suspend fun getLikes() : List<LikeUnit>

    @Query("DELETE FROM $ANIMALS_TAB_LIKE where id = :id")
    suspend fun removeLike(id : String)


}