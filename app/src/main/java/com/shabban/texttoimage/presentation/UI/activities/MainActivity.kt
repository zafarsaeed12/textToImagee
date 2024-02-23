package com.shabban.texttoimage.presentation.UI.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.databinding.ActivityMainBinding
import com.shabban.texttoimage.presentation.UI.fragments.AccountFragment
import com.shabban.texttoimage.presentation.UI.fragments.HomeFragment
import com.shabban.texttoimage.presentation.UI.fragments.LibraryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	var binding : ActivityMainBinding? = null


	companion object{
		var firstTime = true
	}
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding?.root
		setContentView(view)
		
	}

}