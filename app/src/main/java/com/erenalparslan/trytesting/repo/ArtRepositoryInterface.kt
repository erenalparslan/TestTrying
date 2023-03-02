package com.erenalparslan.trytesting.repo

import androidx.lifecycle.LiveData
import com.erenalparslan.trytesting.model.Art
import com.erenalparslan.trytesting.model.ImageResponse
import com.erenalparslan.trytesting.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)
    suspend fun deleteArt(art: Art)
    fun getArt() :LiveData<List<Art>>
    suspend fun searchArt(imageString: String) :Resource<ImageResponse>
}