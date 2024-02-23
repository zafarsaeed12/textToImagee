package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.Common.Deeplinks
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentParentBinding
import com.shabban.texttoimage.presentation.UI.activities.MainActivity


class ParentFragment : Fragment() {
    var binding: FragmentParentBinding? = null
    val TAG = "ParentFragmentTAG"
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (MainActivity.firstTime) {
            findNavController().navigate(Deeplinks.PREMIUM_SCREEN_DEEPLINK.toUri())
            MainActivity.firstTime = false
        }

        val navView: BottomNavigationView? = binding?.bottomNavigationView

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        navView?.setupWithNavController(navController)
    }
}