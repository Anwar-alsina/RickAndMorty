package com.example.rickmorty.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentEpisodeDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.internal.wait

class EpisodeDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpisodeDetailsViewModel by viewModels()
    private val safeArgs: EpisodeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.episodeLiveData.observe(viewLifecycleOwner){episode->
            if (episode == null){
                //handle error
                return@observe
            }

            binding.apply {
                episodeNameTextView.text = episode.name
                episodeAirDateTextView.text = episode.airDate
                episodeNumberTextView.text = episode.getFormattedSeason()
            }

            binding.epoxyRecyclerView.setControllerAndBuildModels(
                EpisodeDetailEpoxyController(episode.characters)
            )
        }

        viewModel.fetchEpisode((safeArgs.episodeId))


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}