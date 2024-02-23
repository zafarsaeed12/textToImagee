package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentPremiumScreenBinding
import com.shabban.texttoimage.presentation.UI.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PremiumScreen : Fragment() {
	lateinit var binding : FragmentPremiumScreenBinding

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater : LayoutInflater, container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? {

		binding = FragmentPremiumScreenBinding.inflate(inflater, container, false)

		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setStatusBarColor(R.color.maincolorbg)

		changeStatusBarTextColor(true)
		binding.btnGetstarted.setOnClickListener {
			findNavController().popBackStack()
		}
		binding.skip.setOnClickListener {
			findNavController().popBackStack()
		}
		binding.weeklyCheck.setImageResource(R.drawable.ic_check)
		binding.weeklyPremium.setBackgroundResource(R.drawable.bg_cardss)
		binding.weeklyPremium.setOnClickListener {
			binding.weeklyCheck.setImageResource(R.drawable.ic_check)
			binding.weeklyPremium.setBackgroundResource(R.drawable.bg_cardss)
			binding.yearlyCheck.setImageResource(R.drawable.ic_uncheck)
			binding.yearlyPremium.setBackgroundResource(R.drawable.bg_card)
			binding.monthlyCheck.setImageResource(R.drawable.ic_uncheck)
			binding.monthlyPremium.setBackgroundResource(R.drawable.bg_card)

		}
		binding.monthlyPremium.setOnClickListener {
			binding.monthlyCheck.setImageResource(R.drawable.ic_check)
			binding.monthlyPremium.setBackgroundResource(R.drawable.bg_cardss)
			binding.weeklyCheck.setImageResource(R.drawable.ic_uncheck)
			binding.weeklyPremium.setBackgroundResource(R.drawable.bg_card)
			binding.yearlyCheck.setImageResource(R.drawable.ic_uncheck)
			binding.yearlyPremium.setBackgroundResource(R.drawable.bg_card)


		}
		binding.yearlyPremium.setOnClickListener {
			binding.yearlyCheck.setImageResource(R.drawable.ic_check)
			binding.yearlyPremium.setBackgroundResource(R.drawable.bg_cardss)
			binding.monthlyCheck.setImageResource(R.drawable.ic_uncheck)
			binding.monthlyPremium.setBackgroundResource(R.drawable.bg_card)
			binding.weeklyCheck.setImageResource(R.drawable.ic_uncheck)
			binding.weeklyPremium.setBackgroundResource(R.drawable.bg_card)

		}


		val bottomView = (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView)
		bottomView?.visibility = View.GONE

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