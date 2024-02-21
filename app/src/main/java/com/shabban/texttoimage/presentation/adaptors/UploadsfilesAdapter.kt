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

class UploadsfilesAdapter(var context : Context, private val imageList: List<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private var imagelist : ArrayList<Modelimages> = ArrayList()


    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : RecyclerView.ViewHolder {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_uploads, parent, false)
        )
    }

    override fun getItemCount() : Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {

    }


    class MainViewHolder(inflate : View) : RecyclerView.ViewHolder(inflate) {

        var image : ImageView

        init {
            image = itemView.findViewById(R.id.imageviewhome)

        }


    }

//    fun setData(list: ArrayList<Modelimages>) {
//        if (list.isNullOrEmpty()) {
//            imageList = ArrayList()
//
//        }
//        imageList = list
//
//        notifyDataSetChanged()
//    }
}