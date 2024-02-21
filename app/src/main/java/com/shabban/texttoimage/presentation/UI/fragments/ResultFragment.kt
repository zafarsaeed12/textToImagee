package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.presentation.UI.activities.MainActivity
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
	lateinit var binding : FragmentResultBinding

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
	
	}
	
	override fun onCreateView(
		inflater : LayoutInflater, container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? {
		binding= FragmentResultBinding.inflate(inflater,container,false)
		
		
		return binding.root
		
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val bottomView = (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
		bottomView?.visibility = View.GONE
		binding.btnBack.setOnClickListener {
			findNavController().popBackStack()
			val bottomView = (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
			bottomView?.visibility = View.VISIBLE
		}
		
	}
	

}