package com.erenalparslan.trytesting.roomdb
import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import androidx.test.filters.SmallTest
import com.erenalparslan.trytesting.dao.ArtDao
import com.erenalparslan.trytesting.db.ArtDatabase
import com.erenalparslan.trytesting.getOrAwaitValue
import com.erenalparslan.trytesting.model.Art

import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

  @Inject
  @Named("testDatabase")
   private lateinit var database : ArtDatabase

   private lateinit  var dao : ArtDao

    @Before
    fun setup(){

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),ArtDatabase::class.java)
            .allowMainThreadQueries() //this is a test case, we don't want other thread pools
            .build()

        dao=database.artDao()
    }





    @Test
    fun insertArtTesting() = runBlocking {


        val exampleArt = Art("Mona Lisa","Da Vinci",1700,"test.com",1)
        dao.insertArt(exampleArt)

        val list = dao.observeArt().getOrAwaitValue()
        assertThat(list).contains(exampleArt)

    }



}