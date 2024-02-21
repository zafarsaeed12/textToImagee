package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentParentBinding


class ParentFragment : Fragment() {
    var binding: FragmentParentBinding? = null

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


   /*     with(binding?.bottomNavigationView) {
            this?.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        }*/

    }


   /* private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val ftt = childFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.home_frag -> {
                    ftt.replace(R.id.fragment_container, HomeFragment())

                }

                R.id.lib_frag -> {
                    ftt.replace(R.id.fragment_container, LibraryFragment())

                }

                R.id.account_frag -> {
                    ftt.replace(R.id.fragment_container, AccountFragment())

                }
            }
            ftt.commit()
            true
        }*/


}