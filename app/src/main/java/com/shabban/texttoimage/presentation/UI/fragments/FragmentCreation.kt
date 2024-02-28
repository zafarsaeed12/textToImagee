package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shabban.texttoimage.Common.inVisible
import com.shabban.texttoimage.Common.visible
import com.shabban.texttoimage.databinding.FragmentCreationBinding
import com.shabban.texttoimage.presentation.adaptors.MyCreationAdapter
import com.shabban.texttoimage.presentation.viewmodels.CreationFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentCreation : Fragment() {
    lateinit var binding: FragmentCreationBinding
    private lateinit var recyclerviewAdapter: MyCreationAdapter
    private val viewModel: CreationFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.progressBar?.show()
        activity?.let { activity ->
            recyclerviewAdapter = MyCreationAdapter(activity)
        }

        binding.rvHome.adapter = recyclerviewAdapter
        observeCreationList()

    }

    private fun observeCreationList() {

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getImagesFromDB().collect { list ->
                binding?.progressBar?.hide()
                if (list.isEmpty()) {
                    binding?.apply {
                        tvNoData.visible()
                        rvHome.inVisible()
                    }
                } else {
                    binding?.apply {
                        tvNoData.inVisible()
                        rvHome.visible()
                    }
                    recyclerviewAdapter.submitList(list)
                }

            }
        }
    }
}