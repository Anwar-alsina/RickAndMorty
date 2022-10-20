package com.example.rickmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickmorty.network.CharacterDetailsEpoxyController
import com.example.rickmorty.viewmodels.SharedViewModel

class CharacterDetailFragment : Fragment() {

    val mSharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }
    private val epoxyController = CharacterDetailsEpoxyController()

    private val safeArgs: CharacterDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSharedViewModel.characterByIdLiveData.observe(viewLifecycleOwner) { character ->

            epoxyController.character = character

            if (character == null) {
                Toast.makeText(
                    requireActivity(),
                    "Unsuccessful network request",
                    Toast.LENGTH_LONG
                ).show()
                findNavController().navigateUp()
                return@observe
            }
        }


       mSharedViewModel.fetchCharacter(characterId = safeArgs.characterId)
        val rvEpoxy = view.findViewById<EpoxyRecyclerView>(R.id.rvEpoxy)
        rvEpoxy.setControllerAndBuildModels(epoxyController)
    }


}