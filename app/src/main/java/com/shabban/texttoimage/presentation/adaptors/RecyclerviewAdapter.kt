package com.shabban.texttoimage.presentation.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shabban.texttoimage.R
import com.shabban.texttoimage.presentation.Interfaces.OnItemClick
import com.shabban.texttoimage.presentation.models.Modelimages

class RecyclerviewAdapter(var context : Context, private val listener : OnItemClick) :
	RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	
	private var imagelist : ArrayList<Modelimages> = ArrayList()
	
	
	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerView.ViewHolder {
		return MainViewHolder(
			LayoutInflater.from(context).inflate(R.layout.rv_items, parent, false)
		)
	}
	
	override fun getItemCount() : Int {
		return imagelist.size
	}
	
	override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {
	
	}
	
	
	class MainViewHolder(inflate : View) : RecyclerView.ViewHolder(inflate) {
		
		var image : ImageView
		
		init {
			image = itemView.findViewById(R.id.img1)
			
		}
		
		
	}
	
	fun setData(list: ArrayList<Modelimages>) {
		if (list.isNullOrEmpty()) {
			imagelist = ArrayList()
			
		}
		imagelist = list
		
		notifyDataSetChanged()
	}
}