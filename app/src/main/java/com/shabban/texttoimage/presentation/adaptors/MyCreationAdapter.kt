package com.shabban.texttoimage.presentation.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shabban.texttoimage.Data.room.ImageEntity
import com.shabban.texttoimage.databinding.MyCreationItemBinding
import com.shabban.texttoimage.presentation.callback.MyCreationAdapterDiffUtil

class MyCreationAdapter(
    val context: Context
):
    ListAdapter<ImageEntity, MyCreationViewHolder>(MyCreationAdapterDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCreationViewHolder {

        val binding =
            MyCreationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCreationViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyCreationViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(context,currentItem)

    }

}


