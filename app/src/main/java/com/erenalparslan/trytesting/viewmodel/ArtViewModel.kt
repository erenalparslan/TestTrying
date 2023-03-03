package com.erenalparslan.trytesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erenalparslan.trytesting.model.Art
import com.erenalparslan.trytesting.model.ImageResponse
import com.erenalparslan.trytesting.repo.ArtRepositoryInterface
import com.erenalparslan.trytesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel
@Inject
    constructor(private var repository: ArtRepositoryInterface
   )
    : ViewModel() {
        //  Art Fragment

        val artList =repository.getArt()

        //  Image Api

    private val images =MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage =MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage

    //  ART DETAİL FRAGMENT

    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
        get() = insertArtMsg

    fun resetInserArtMsg(){
         insertArtMsg = MutableLiveData<Resource<Art>>()
    }
    fun setImage(url :String){
        selectedImage.postValue(url)
    }


    // repository.deleteArt suspend fun olduğu için Scope içinde yapmamız gerekiyor.Coroutine bunun için var.

    fun deleteArt(art :Art)=viewModelScope.launch{
        repository.deleteArt(art)
    }
    fun insertArt(art :Art)=viewModelScope.launch {
        repository.insertArt(art)
    }

    fun searchForImage(serachString: String){
        if(serachString.isEmpty()){
        return
        }
        images.value=Resource.loading(null)
        viewModelScope.launch {
            val response=repository.searchArt(serachString)
            images.value=response
        }
    }

    fun makeArt(artName :String,artistName :String,year :String){
        if(artName.isEmpty()||artistName.isEmpty()||year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Entry name,artist,year",null))
            return
        }
        val yearInt=try {
            year.toInt()
        }catch (e :Exception){
            insertArtMsg.postValue(Resource.error("Year should be number",null))
        return
        }

        val art =Art(artName,artistName,yearInt,selectedImage.value?: "")
        insertArt(art)
        selectedImage.value=""
        insertArtMsg.postValue(Resource.success(art))



    }



}

