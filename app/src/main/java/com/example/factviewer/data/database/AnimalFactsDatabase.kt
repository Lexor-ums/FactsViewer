package com.example.factviewer.data.database

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.factviewer.data.database.dao.AnimalFactsDAO
import com.example.factviewer.data.database.dao.LikesDao
import com.example.factviewer.data.database.entity.AnimalFactUnit
import com.example.factviewer.data.database.entity.LikeUnit

@Database(entities = [AnimalFactUnit::class, LikeUnit::class], version = 1)
abstract class AnimalFactsDatabase : RoomDatabase(){
    abstract fun animalFactsDAO() : AnimalFactsDAO
    abstract fun likesDAO() : LikesDao
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}