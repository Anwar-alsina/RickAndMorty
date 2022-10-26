package com.example.rickmorty.characters.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.rickmorty.R
import com.example.rickmorty.databinding.FragmentCharacterSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterSearchFragment : Fragment() {

    private var _binding: FragmentCharacterSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterSearchViewModel by viewModels()

    private var currentText = ""
    private val handler = Handler(Looper.getMainLooper())
    private val searchRunnable = Runnable{
        viewModel.submitQuery(currentText)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = CharacterSearchEpoxyController { characterId ->
            // todo navigate to details page with ID
        }

        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)

        binding.searchEditText.doAfterTextChanged {
            currentText = it?.toString() ?: ""

            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable,500L)
        }
        lifecycleScope.launch{
            viewModel.flow.collectLatest { pagingData->
                epoxyController.localException = null
                epoxyController.submitData(pagingData)
            }
        }

        viewModel.localExceptionLiveData.observe(viewLifecycleOwner){event ->
            event.getContent()?.let {localException ->
            epoxyController.localException = localException
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}