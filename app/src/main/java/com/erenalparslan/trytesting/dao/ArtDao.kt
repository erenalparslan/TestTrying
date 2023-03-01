package com.erenalparslan.trytesting.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.erenalparslan.trytesting.model.Art

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("SELECT * FROM arts")
    fun observeArt(): LiveData<List<Art>>
}
