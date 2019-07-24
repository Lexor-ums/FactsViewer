package com.example.factviewer.domain.database

import androidx.room.Entity
import androidx.room.Ignore
import com.example.factviewer.utils.ANIMALS_TAB_LIKE
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"], tableName = ANIMALS_TAB_LIKE)
data class LikeUnit (
    @field:SerializedName("id")
    var id : String = ""
)