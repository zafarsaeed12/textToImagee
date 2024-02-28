package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentUploadBinding
import com.shabban.texttoimage.presentation.Interfaces.OnItemClick
import com.shabban.texttoimage.presentation.adaptors.UploadsfilesAdapter

class FragmentUpload : Fragment(), OnItemClick {
    lateinit var binding: FragmentUploadBinding
    lateinit var uploadsfilesAdapter: UploadsfilesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUploadBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadsfilesAdapter = UploadsfilesAdapter(requireContext(), imageList)
        binding.rvHome.layoutManager = GridLayoutManager(context, 3)
        binding.rvHome.adapter = uploadsfilesAdapter
//		uploadsfilesAdapter.setData(images)

        binding.rvUploads.layoutManager = GridLayoutManager(context, 3)
        binding.rvUploads.adapter = uploadsfilesAdapter
//		uploadsfilesAdapter.setData(images)

    }

    val imageList = listOf(
        R.drawable.ic_home_img_small,
        R.drawable.ic_home_img_small,
        R.drawable.ic_home_img_small,
        R.drawable.ic_imgs,
        R.drawable.ic_imgs,
        R.drawable.ic_imgs,
        R.drawable.ic_home_img_small,
        R.drawable.ic_home_img_small,
        R.drawable.ic_home_img_small,
		R.drawable.ic_imgs,
		R.drawable.ic_imgs,
		R.drawable.ic_imgs

    )

    override fun itemclick(position: Int) {
        TODO("Not yet implemented")
    }
}