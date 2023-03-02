package com.erenalparslan.trytesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.erenalparslan.trytesting.R
import com.erenalparslan.trytesting.databinding.FragmentArtBinding

class ArtFragment : Fragment(R.layout.fragment_art) {
    private var artFragmentBinding : FragmentArtBinding?=null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var binding = FragmentArtBinding.bind(view)
        artFragmentBinding = binding


        binding.fab.setOnClickListener{
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailFragment())
        }
    }

    override fun onDestroyView() {
       artFragmentBinding=null
        super.onDestroyView()
    }

}