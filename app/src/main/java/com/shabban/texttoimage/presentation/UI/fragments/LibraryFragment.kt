package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentLibraryBinding
import com.shabban.texttoimage.presentation.adaptors.ViewPagerAdapter


class LibraryFragment : Fragment() {
	lateinit var binding : FragmentLibraryBinding
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		
	}
	
	override fun onCreateView(
		inflater : LayoutInflater, container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? {
		// Inflate the layout for this fragment
		binding = FragmentLibraryBinding.inflate(inflater, container, false)
		
		
		return binding.root
	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val adapter = ViewPagerAdapter(childFragmentManager)
		binding.viewPager.adapter = adapter
		
		binding.btnMycreation.setOnClickListener {
			binding.viewPager.setCurrentItem(0)
			
		}
		binding.btnMyUploads.setOnClickListener {
			binding.viewPager.setCurrentItem(1)
		}
		binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
			override fun onPageScrolled(
				position : Int,
				positionOffset : Float,
				positionOffsetPixels : Int
			) {
				// Not used
			}
			
			override fun onPageSelected(position : Int) {
				when (position) {
					0 -> {
						binding.btnMycreation.setBackgroundResource(
							R.drawable.bg_buttons,
						)
						binding.btnMycreation.setTextColor(getResources().getColor(R.color.white))
						
						binding.btnMyUploads.setBackgroundResource(
							R.drawable.bg_home
						)
						binding.btnMyUploads.setTextColor(getResources().getColor(R.color.black))
						
						
					}
					
					1 -> {
						binding.btnMycreation.setBackgroundResource(
							R.drawable.bg_home
						)
						binding.btnMycreation.setTextColor(getResources().getColor(R.color.black))
						
						binding.btnMyUploads.setBackgroundResource(
							R.drawable.bg_buttons
						)
						binding.btnMyUploads.setTextColor(getResources().getColor(R.color.white))
						
						
					}
				}
			}
			
			override fun onPageScrollStateChanged(state : Int) {
				// Not used
			}
		})
		
	}
	
	
}