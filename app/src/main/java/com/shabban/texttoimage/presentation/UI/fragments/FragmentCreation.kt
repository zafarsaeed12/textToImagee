package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentCreationBinding
import com.shabban.texttoimage.presentation.Interfaces.OnItemClick
import com.shabban.texttoimage.presentation.adaptors.RecyclerviewAdapter
import com.shabban.texttoimage.presentation.adaptors.ViewPagerAdapter
import com.shabban.texttoimage.presentation.models.Modelimages

class FragmentCreation : Fragment() ,OnItemClick{
	lateinit var binding : FragmentCreationBinding
		lateinit var recyclerviewAdapter : RecyclerviewAdapter
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		
		
	}
	
	override fun onCreateView(
		inflater : LayoutInflater, container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? {
		binding=FragmentCreationBinding.inflate(inflater,container,false)
		
		return binding.root
	}
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		recyclerviewAdapter = RecyclerviewAdapter(requireContext(),this)
		getitemlist()
		binding.rvHome.layoutManager =
			LinearLayoutManager(context)
		binding.rvHome.adapter = recyclerviewAdapter
		recyclerviewAdapter.setData(images)
		
	}
	val images = ArrayList<Modelimages>()
	
	fun getitemlist(){
		
		
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		images.add(Modelimages(R.drawable.ic_imgs))
		
	}
	
	override fun itemclick(position : Int) {
		TODO("Not yet implemented")
	}
	
}