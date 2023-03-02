package com.erenalparslan.trytesting.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.erenalparslan.trytesting.dao.ArtDao
import com.erenalparslan.trytesting.model.Art

@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase :RoomDatabase(){
    abstract fun artDao() : ArtDao
}