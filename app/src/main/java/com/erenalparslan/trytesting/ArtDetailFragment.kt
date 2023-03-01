package com.erenalparslan.trytesting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erenalparslan.trytesting.databinding.FragmentArtDetailBinding


class ArtDetailFragment : Fragment() {
        private var artDetailBinding : FragmentArtDetailBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_art_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentArtDetailBinding.bind(view)
        artDetailBinding=binding

        binding.save.setOnClickListener{
            findNavController().navigate(ArtDetailFragmentDirections.actionArtDetailFragmentToApiFragment())

        }

        super.onViewCreated(view, savedInstanceState)
    }


}