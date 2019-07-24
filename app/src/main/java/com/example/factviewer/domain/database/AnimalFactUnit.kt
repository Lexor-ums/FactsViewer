package com.example.factviewer.domain.database

import androidx.room.Entity
import androidx.room.Ignore
import com.example.factviewer.utils.ANIMALS_TAB_NAME
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"], tableName = ANIMALS_TAB_NAME)
data class AnimalFactUnit (
    @field:SerializedName("id")
    var id : String = "",
    @field:SerializedName("animal")
    var animal : String = "",
    @field:SerializedName("fact")
    var fact : String = ""
)