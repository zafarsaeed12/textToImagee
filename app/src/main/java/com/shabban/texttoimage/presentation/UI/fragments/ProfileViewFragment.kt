package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentProfileViewBinding


class ProfileViewFragment : Fragment() {
lateinit var binding : FragmentProfileViewBinding
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
	
	}
	
	override fun onCreateView(
		inflater : LayoutInflater, container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? {
      binding=FragmentProfileViewBinding.inflate(inflater,container,false)
		setStatusBarColor(R.color.profileclor)
		
		changeStatusBarTextColor(true)
		
		
		
		
		return binding.root


	}
	
	override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.btnBack.setOnClickListener {
			findNavController().popBackStack()
			
		}
		
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
	
}