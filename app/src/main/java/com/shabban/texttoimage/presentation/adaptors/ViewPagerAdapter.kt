package com.shabban.texttoimage.presentation.adaptors

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shabban.texttoimage.presentation.UI.fragments.FragmentCreation
import com.shabban.texttoimage.presentation.UI.fragments.FragmentUpload

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
	override fun getItem(position: Int): Fragment {
		return when (position) {
			0 -> FragmentCreation()
			1 -> FragmentUpload()
			else -> throw IllegalArgumentException("Invalid position")
		}
	}
	
	override fun getCount(): Int {
		return 2
	}
}
