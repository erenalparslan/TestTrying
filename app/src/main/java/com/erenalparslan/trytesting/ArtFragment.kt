package com.erenalparslan.trytesting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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

}