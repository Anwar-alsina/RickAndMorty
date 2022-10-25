package com.example.rickmorty.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickmorty.NavGraphDirections
import com.example.rickmorty.R
import com.example.rickmorty.characters.CharacterListFragmentDirections
import com.example.rickmorty.databinding.FragmentEpisodeBinding
import com.example.rickmorty.domain.models.Episode
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodeFragment : Fragment() {

    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpisodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodeBinding.bind(view)

        val epoxyController = EpisodeListEpoxyController{episodeClickedId ->
            val navDirections =
                NavGraphDirections.actionGlobalToEpisodeDetailFragment(episodeId = episodeClickedId)

            findNavController().navigate(navDirections)
        }

        lifecycleScope.launch{
            viewModel.flow.collectLatest {pagingData: PagingData<EpisodesUiModel> ->
                epoxyController.submitData(pagingData)
            }
        }
        binding.epoxyRecyclerView.setController(epoxyController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}