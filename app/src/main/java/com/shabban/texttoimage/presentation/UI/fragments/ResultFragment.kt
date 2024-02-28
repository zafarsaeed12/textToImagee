package com.shabban.texttoimage.presentation.UI.fragments

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.Common.Constant
import com.shabban.texttoimage.Common.isWriteStoragePermissionGranted
import com.shabban.texttoimage.Common.loadImageWithProgress
import com.shabban.texttoimage.Common.saveBase64Image
import com.shabban.texttoimage.Common.showShortToast
import com.shabban.texttoimage.Common.showSnackBar
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Data.model.response.ImageResponse
import com.shabban.texttoimage.Data.room.ImageEntity
import com.shabban.texttoimage.R
import com.shabban.texttoimage.Utils.Resource
import com.shabban.texttoimage.databinding.FragmentResultBinding
import com.shabban.texttoimage.presentation.UI.activities.MainActivity
import com.shabban.texttoimage.presentation.viewmodels.HomeSharedViewModel
import com.shabban.texttoimage.presentation.viewmodels.ResultFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ResultFragment : Fragment() {
    private val homeSharedViewModel: HomeSharedViewModel by activityViewModels()
    private val viewModel: ResultFragmentViewModel by viewModels()
    val TAG = "ResultFragmentTAG"

    private var currentImageUrl: String? = Constant.EMPTY_STRING
    private var currentImageBase64: String? = Constant.EMPTY_STRING

    private val binding: FragmentResultBinding by lazy {
        FragmentResultBinding.inflate(layoutInflater)
    }

    val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {

            } else {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()


        val bottomView =
            (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomView?.visibility = View.GONE

        getImage()
    }

    private fun getImage() {
        homeSharedViewModel.imageUrlLiveData.value?.let {
            val url = it.stabilityai?.items?.get(0)?.imageResourceUrl
            val imageBase64 = it.stabilityai?.items?.get(0)?.image
            currentImageUrl = url
            currentImageBase64 = imageBase64
            setImageByUrl()
        }
    }

    private fun setImageByUrl() {
        //binding?.ivImage?.load(currentImageUrl)
        val imageLoader = ImageLoader.Builder(requireContext()).build()
        binding?.progressBar?.let {
            currentImageUrl?.let { it1 ->
                binding?.ivImage?.loadImageWithProgress(
                    it1, imageLoader,
                    it, {}, {})
            }
        }
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

            btnDownload?.setOnClickListener {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    if (activity?.isWriteStoragePermissionGranted() == false) {
                        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    } else {
                        downloadImage()
                    }
                } else {
                    downloadImage()
                }


            }

            btnSave.setOnClickListener {
                handleSaveImage()
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
                    activity?.showShortToast("success while generating image")
                    onImageGenerationSuccess(response.data)
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

    private fun onImageGenerationSuccess(imageResponse: ImageResponse?) {
        val imageUrl = imageResponse?.stabilityai?.items?.get(0)?.imageResourceUrl
        val image64 = imageResponse?.stabilityai?.items?.get(0)?.image
        currentImageUrl = imageUrl
        currentImageBase64 = image64
        setImageByUrl()
    }

    private fun handleSaveImage() {
        if (currentImageUrl.isNullOrEmpty() || currentImageBase64.isNullOrEmpty()) {
            showShortToast("Something went wrong while saving.")

        } else {
            val imageEntity = ImageEntity(
                imageUrl = currentImageUrl, imageBase64 = currentImageBase64
            )
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.saveImageToDB(imageEntity)
            }
            showShortToast("Image saved successfully")
        }

    }

    private fun downloadImage() {
        val isDownloaded = activity?.saveBase64Image(viewModel.currentImageBase64, "girl")
        if (isDownloaded == true) {
            showShortToast("successfully downloaded")
        } else {
            showShortToast("downloading failed")
        }

    }

}