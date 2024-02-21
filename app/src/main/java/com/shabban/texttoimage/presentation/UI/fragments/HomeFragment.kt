package com.shabban.texttoimage.presentation.UI.fragments

import android.app.Dialog
import android.content.ContentValues.TAG
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
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.shabban.texttoimage.Common.UiState
import com.shabban.texttoimage.Common.showShortToast
import com.shabban.texttoimage.Data.repository.imagesRepostryImpl
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentHomeBinding
import com.shabban.texttoimage.presentation.Interfaces.OnItemClick
import com.shabban.texttoimage.presentation.adaptors.RecyclerviewAdapter
import com.shabban.texttoimage.presentation.viewmodels.ChatViewModelFactory
import com.shabban.texttoimage.presentation.models.Modelimages
import com.shabban.texttoimage.presentation.viewmodels.imagesViewModel
import kotlinx.coroutines.launch
import java.util.Objects


class HomeFragment : Fragment(), OnItemClick {
    lateinit var binding: FragmentHomeBinding
    lateinit var recyclerviewAdapter: RecyclerviewAdapter
    lateinit var chatViewModel: imagesViewModel
    private var counter = 0
    private val handler = Handler(Looper.getMainLooper())
    private val progressUpdateInterval = 100L // Interval in milliseconds
    private val progressMax = 100
     var codeDialog : Dialog? = null


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
        chatViewModel = ViewModelProvider(
            this, ChatViewModelFactory(imagesRepostryImpl())
        )[imagesViewModel::class.java]
        initObservers()
        val bottomSheetFragment = ItemListDialogFragment()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        recyclerviewAdapter = RecyclerviewAdapter(requireContext(), this)
        getitemlist()
        binding.rvHome.layoutManager =
            LinearLayoutManager(context)
        binding.rvHome.adapter = recyclerviewAdapter
        recyclerviewAdapter.setData(images)
        binding.btnGenrate.setOnClickListener {
            startProgress()
//            findNavController().navigate(R.id.resultFragment)

        }
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
                    val progressBar: CircularProgressBar? =
                        codeDialog?.findViewById(R.id.downloadingProgress)
                    progressTv?.text = "$counter%"
                    progressBar?.progress = counter.toFloat()
                    counter++
                    handler.postDelayed(this, progressUpdateInterval)
                    codeDialog?.show()
                } else {
                    // Progress completed
                    codeDialog?.dismiss()
                    findNavController().navigate(R.id.resultFragment)


                }

            }

        })
    }


}