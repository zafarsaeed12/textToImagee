package com.shabban.texttoimage.presentation.UI.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shabban.texttoimage.R
import com.shabban.texttoimage.databinding.FragmentSplashScreenBinding


class SplashScreen : Fragment() {
    lateinit var binding: FragmentSplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        setStatusBarColor(R.color.profileclor)

        changeStatusBarTextColor(true)
        binding.btnGetstarted.setOnClickListener {

            // findNavController().navigate(Deeplinks.PREMIUM_SCREEN_DEEPLINK.toUri())
            findNavController().navigate(R.id.toParentFragment)

        }
        val bottomView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomView?.visibility = View.GONE



        return binding.root
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

//package com.shabban.texttoimage.presentation.adaptors
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.recyclerview.widget.RecyclerView
//import com.shabban.texttoimage.R
//import com.shabban.texttoimage.presentation.Interfaces.OnItemClick
//import com.shabban.texttoimage.presentation.models.Modelimages
//
//class RecyclerviewAdapter(var imagelist :  ArrayList<Modelimages>
//
//) :
//	RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
////	private var imagelist : ArrayList<Modelimages> = ArrayList()
//	private val SMALL_ITEM = 0
//	private val BIG_ITEM = 1
//
//	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerView.ViewHolder {
//		return when (viewType) {
//
//
//			SMALL_ITEM -> {
//				val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_items_small, parent, false)
//				SmallItemViewHolder(view)
//			}
//			BIG_ITEM->{
//				val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_items, parent, false)
//				BigItemViewHolder(view)
//			}
//
//
//			else -> throw IllegalArgumentException("Invalid view type")
//		}
//
//
//	}
//
//	override fun getItemCount() : Int {
//		return imagelist.size
//	}
//
//	override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {
//
//		val item = imagelist[position]
//
//		when (holder.itemViewType) {
//			SMALL_ITEM -> {
//				(holder as SmallItemViewHolder).bind(item)
//			}
//			BIG_ITEM -> {
//				(holder as BigItemViewHolder).bind(item)
//			}
//		}
//	}
//
//
////	class MainViewHolder(inflate : View) : RecyclerView.ViewHolder(inflate) {
////
////		var image : ImageView
////
////		init {
////			image = itemView.findViewById(R.id.imageviewhome)
////
////		}
////
////
////	}
//
//	inner class SmallItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//		private val smallImageView: ImageView = itemView.findViewById(R.id.smallImageView)
//
//		fun bind(item : Modelimages) {
//			// Bind small item data here
//			smallImageView.setImageResource(item.image)
//		}
//
//
//	}
//
//	inner class BigItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//		private val bigImageView: ImageView = itemView.findViewById(R.id.imageviewhome)
//
//		fun bind(item: Modelimages) {
//			bigImageView.setImageResource(item.image)
//		}
//	}
//
//	override fun getItemViewType(position: Int): Int {
//		return if (position % 3 == 0) BIG_ITEM else SMALL_ITEM
//	}
//
//
//	fun setData(list: ArrayList<Modelimages>) {
//		if (list.isNullOrEmpty()) {
//			imagelist = ArrayList()
//
//		}
//		imagelist = list
//
//		notifyDataSetChanged()
//	}
//}