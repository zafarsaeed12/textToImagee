package com.shabban.texttoimage.presentation.UI.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.shabban.texttoimage.Common.Constant
import com.shabban.texttoimage.Common.UiState
import com.shabban.texttoimage.Common.showShortToast
import com.shabban.texttoimage.Common.showSnackBar
import com.shabban.texttoimage.Data.model.request.ImageRequest
import com.shabban.texttoimage.Domain.GenerateImageUsecase
import com.shabban.texttoimage.R
import com.shabban.texttoimage.Utils.Resource
import com.shabban.texttoimage.databinding.FragmentHomeBinding
import com.shabban.texttoimage.presentation.Interfaces.OnItemClick
import com.shabban.texttoimage.presentation.adaptors.RecyclerviewAdapter
import com.shabban.texttoimage.presentation.models.Modelimages
import com.shabban.texttoimage.presentation.viewmodels.HomeFragmentViewModel
import com.shabban.texttoimage.presentation.viewmodels.HomeSharedViewModel
import com.shabban.texttoimage.presentation.viewmodels.imagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemClick {
    val TAG = "HomeFragmentTAG"
    lateinit var binding: FragmentHomeBinding
    lateinit var recyclerviewAdapter: RecyclerviewAdapter
    lateinit var chatViewModel: imagesViewModel
    private var counter = 0
    private val handler = Handler(Looper.getMainLooper())
    private val progressUpdateInterval = 100L // Interval in milliseconds
    private val progressMax = 100
    var codeDialog: Dialog? = null
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val homeSharedViewModel: HomeSharedViewModel by activityViewModels()

    @Inject
    lateinit var generateImageUsecase: GenerateImageUsecase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setStatusBarColor(R.color.maincolorbg)

        changeStatusBarTextColor(true)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        codeDialog = Dialog(requireContext())
        val bottomSheetFragment = ItemListDialogFragment()
//        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        recyclerviewAdapter = RecyclerviewAdapter(requireContext(), this)
        getitemlist()
        binding.rvHome.layoutManager =
            LinearLayoutManager(context)
        binding.rvHome.adapter = recyclerviewAdapter
        recyclerviewAdapter.setData(images)
        binding.btnPremium.setOnClickListener {
            Toast.makeText(context, "primium screen", Toast.LENGTH_SHORT).show()

//            findNavController().navigate(R.id.homeToPremmiumScreen)
        }

        binding.btnGenrate.setOnClickListener {

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Notify the activity that this fragment will handle the back press event
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Toast.makeText(context, "exit dailuge", Toast.LENGTH_SHORT).show()
            exittDailuge()
        }
    }

    private fun checkEditTextValue(): Boolean? {
        return binding?.etInput?.text?.toString()?.isEmpty()

    }


    override fun itemclick(position: Int) {
        TODO("Not yet implemented")
    }

    val images = ArrayList<Modelimages>()

    fun getitemlist() {
        images.add(Modelimages(R.drawable.ic_home_img))
        images.add(Modelimages(R.drawable.ic_home_img_small))
        images.add(Modelimages(R.drawable.ic_home_img))
        images.add(Modelimages(R.drawable.ic_home_img_small))
        images.add(Modelimages(R.drawable.ic_home_img))
        images.add(Modelimages(R.drawable.ic_home_img_small))
        images.add(Modelimages(R.drawable.ic_home_img))
        images.add(Modelimages(R.drawable.ic_home_img_small))
        images.add(Modelimages(R.drawable.ic_home_img))
        images.add(Modelimages(R.drawable.ic_home_img_small))
        images.add(Modelimages(R.drawable.ic_home_img))

    }

    private fun changeStatusBarTextColor(light: Boolean) {
        activity?.window?.let { window ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                var flags = window.decorView.systemUiVisibility
                flags = if (light) {
                    flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                window.decorView.systemUiVisibility = flags
            }
        }
    }

    private fun setStatusBarColor(colorResId: Int) {
        activity?.window?.let { window ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(requireContext(), colorResId)
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                chatViewModel.chatState.collect {
                    when (it) {
                        is UiState.Error -> {

                            showShortToast(
                                if (!isNetworkAvailable()) "No internet available"
                                else it.throwable.message ?: "Something went wrong"
                            )
                            Log.e("TESTTAG", "${it.throwable.message}")
                        }

                        is UiState.Loading -> {
                            showShortToast("loading")
                        }

                        is UiState.Success -> {
                            showShortToast("Sucess")

                        }
                    }
                }
            }

        }

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun startProgress() {
        handler?.post(object : Runnable {
            override fun run() {
                if (counter <= progressMax) {
                    codeDialog?.setCancelable(false)
                    Objects.requireNonNull(codeDialog?.window)
                        ?.setBackgroundDrawableResource(android.R.color.transparent)
                    codeDialog?.setContentView(R.layout.dailuge)
                    codeDialog?.window?.setLayout(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    val progressTv: TextView? = codeDialog?.findViewById(R.id.progressTv)
                    val progressBar: CircularProgressIndicator? =
                        codeDialog?.findViewById(R.id.downloadingProgress)
                    progressTv?.text = "$counter%"
                    progressBar?.progress = counter
                    counter++
                    handler.postDelayed(this, progressUpdateInterval)
                    codeDialog?.show()
                } else {
                    // Progress completed
                    codeDialog?.dismiss()
                    //   findNavController().navigate(R.id.resultFragment)


                }

            }

        })
    }

    private fun exittDailuge(): AlertDialog? {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout. exitdailuge, null)
        val cancelbutton: Button? = view?.findViewById(R.id.cancelButton)
        val yesbutton: Button? = view?.findViewById(R.id.yesButton)
        cancelbutton?.setOnClickListener {


        }
        yesbutton?.setOnClickListener {
            requireActivity().finishAffinity()
        }


        builder.setView(view)
        return builder.create()


    }


    private suspend fun generateImage(imageRequest: ImageRequest) {

        viewModel.generateImage(imageRequest).collect { response ->
            when (response) {
                is Resource.Loading -> {
                    startProgress()
                }

                is Resource.Success -> {
                    response?.data?.let { homeSharedViewModel.setImageData(it) }
                    findNavController().navigate(R.id.resultFragment)
                    activity?.showShortToast("Success while generating image")
                }

                is Resource.UnSuccess -> {
                    activity?.showShortToast("Unsiccess ${response.unSuccessMessage}")
                }

                is Resource.Error -> {
                    activity?.showShortToast("Error ${response.errorMessage}")
                }
            }
        }

    }

}