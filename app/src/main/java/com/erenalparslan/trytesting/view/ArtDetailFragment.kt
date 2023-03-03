package com.erenalparslan.trytesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.erenalparslan.trytesting.R
import com.erenalparslan.trytesting.databinding.FragmentArtDetailBinding
import com.erenalparslan.trytesting.util.Status
import com.erenalparslan.trytesting.viewmodel.ArtViewModel
import javax.inject.Inject


class ArtDetailFragment
    @Inject
    constructor(
        private val glide :RequestManager)
    : Fragment() {
    private var artDetailBinding: FragmentArtDetailBinding? = null


    lateinit var viewModel : ArtViewModel



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
        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binding = FragmentArtDetailBinding.bind(view)
        artDetailBinding = binding

        subscribeToObservers()

        binding.imageView.setOnClickListener {
            findNavController().navigate(
                ArtDetailFragmentDirections.actionArtDetailFragmentToApiFragment()
            )
        }


        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.setImage("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        binding.save.setOnClickListener {
            viewModel.makeArt(binding.nameText.text.toString(),
                binding.artistText.text.toString(),
                binding.yearText.text.toString())

        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun subscribeToObservers() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            println(url)
            artDetailBinding?.let {
                glide.load(url).into(it.imageView)
            }
        })

        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireActivity(),"Success", Toast.LENGTH_LONG).show()
                    findNavController().navigateUp()
                    viewModel.resetInserArtMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message ?: "Error",Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }
            }
        })
    }




    override fun onDestroyView() {
        artDetailBinding = null
        super.onDestroyView()
    }


}