package com.erenalparslan.trytesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.erenalparslan.trytesting.adapter.ArtRecyclerAdapter
import com.erenalparslan.trytesting.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory
@Inject constructor(
    private val glide :RequestManager,
private val artRecyclerAdapter: ArtRecyclerAdapter,
private val imageRecyclerAdapter: ImageRecyclerAdapter,
):FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ApiFragment::class.java.name -> ApiFragment(imageRecyclerAdapter)
            ArtDetailFragment::class.java.name -> ArtDetailFragment(glide)
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            else ->super.instantiate(classLoader, className)
        }


    }
}