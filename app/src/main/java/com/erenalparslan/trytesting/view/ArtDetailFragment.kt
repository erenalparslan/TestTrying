package com.erenalparslan.trytesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.erenalparslan.trytesting.R
import com.erenalparslan.trytesting.databinding.FragmentArtDetailBinding
import javax.inject.Inject


class ArtDetailFragment
    @Inject
    constructor(
        private val glide :RequestManager)
    : Fragment() {
    private var artDetailBinding: FragmentArtDetailBinding? = null

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
        artDetailBinding = binding

        binding.imageView.setOnClickListener {
            findNavController().navigate(ArtDetailFragmentDirections.actionArtDetailFragmentToApiFragment())

        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack() //Geriye git ve bunu kapat
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        artDetailBinding = null
        super.onDestroyView()
    }


}