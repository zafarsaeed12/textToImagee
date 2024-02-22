package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.Common.Constant
import com.shabban.texttoimage.Common.showShortToast
import com.shabban.texttoimage.Common.showSnackBar
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.R
import com.shabban.texttoimage.Utils.Resource
import com.shabban.texttoimage.databinding.FragmentResultBinding
import com.shabban.texttoimage.presentation.UI.activities.MainActivity
import com.shabban.texttoimage.presentation.viewmodels.HomeSharedViewModel
import com.shabban.texttoimage.presentation.viewmodels.ResultFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding
    private val homeSharedViewModel: HomeSharedViewModel by activityViewModels()
    private val viewModel: ResultFragmentViewModel by viewModels()
    val TAG = "ResultFragmentTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()


        val bottomView =
            (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomView?.visibility = View.GONE

        getImage()
    }

    fun getImage() {
        homeSharedViewModel.imageUrlLiveData.value?.let {
            val url = it.stabilityai?.items?.get(0)?.imageResourceUrl
            setImage(url)
        }
    }

    fun setImage(url: String?) {
        binding?.ivImage?.load(url)
    }

    private fun initClickListeners() {
        binding?.apply {


            //generate image listener
            btnGenerate.setOnClickListener {
                if (checkEditTextValue() == false) {
                    val inputText = binding?.etInput?.text.toString()

                    lifecycleScope.launch {
                        generateImage(
                            ImageRequest(
                                false,
                                Constant.EMPTY_STRING,
                                Constant.STABILITY_AI_PROVIDER,
                                inputText,
                                Constant.RESOLUTION,
                                1
                            )
                        )
                    }
                } else {
                    binding?.root?.showSnackBar("Invalid text")
                }
            }
//back listener
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }


    }

    private fun checkEditTextValue(): Boolean? {
        return binding?.etInput?.text?.toString()?.isEmpty()

    }

    private suspend fun generateImage(imageRequest: ImageRequest) {

        viewModel.generateImage(imageRequest).collect { response ->
            when (response) {
                is Resource.Loading -> {
                    activity?.showShortToast("loading while generating image")
                }

                is Resource.Success -> {
                    val url = response?.data?.openai?.items?.get(0)?.imageResourceUrl
                    onImageGenerationSuccess(url)
                }

                is Resource.UnSuccess -> {
                    activity?.showShortToast("Unsuccess while generating image")
                }

                is Resource.Error -> {
                    activity?.showShortToast("Error while generating image")
                }
            }
        }

    }

    private fun onImageGenerationSuccess(imageUrl: String?) {
        setImage(imageUrl)
    }

}