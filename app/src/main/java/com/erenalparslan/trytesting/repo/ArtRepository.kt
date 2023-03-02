package com.erenalparslan.trytesting.repo

import androidx.lifecycle.LiveData
import com.erenalparslan.trytesting.api.RetrofitApi
import com.erenalparslan.trytesting.dao.ArtDao
import com.erenalparslan.trytesting.model.Art
import com.erenalparslan.trytesting.model.ImageResponse
import com.erenalparslan.trytesting.util.Resource
import com.erenalparslan.trytesting.util.Util.API_KEY
import javax.inject.Inject

class ArtRepository
@Inject
constructor(
    private var artDao: ArtDao,
    private var retrofitApi: RetrofitApi)  : ArtRepositoryInterface {

    override suspend fun insertArt(art: Art) {
      artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
      artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
    return artDao.observeArt()
    }

    override suspend fun searchArt(imageString: String): Resource<ImageResponse> {
        return try {
            var response = retrofitApi.imageSearch(imageString)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data!",null)
            }
            else{
                Resource.error("No Data !",null)
            }
        }
        catch(e:Exception){
            Resource.error("No Data",null)

        }
    }
}