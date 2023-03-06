package com.erenalparslan.trytesting.dependecyinjection

import android.content.Context
import androidx.room.Room
import com.erenalparslan.trytesting.db.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    @Named("testDatabase")
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        ArtDatabase::class.java).allowMainThreadQueries().build()
}