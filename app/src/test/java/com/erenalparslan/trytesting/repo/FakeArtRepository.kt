package com.erenalparslan.trytesting.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erenalparslan.trytesting.model.Art
import com.erenalparslan.trytesting.model.ImageResponse
import com.erenalparslan.trytesting.util.Resource


class FakeArtRepository : ArtRepositoryInterface {

    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>(arts)



    private fun refreshLiveData() {
        artsLiveData.postValue(arts)
    }

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshLiveData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshLiveData()
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchArt(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(0,0,listOf()))
    }


}