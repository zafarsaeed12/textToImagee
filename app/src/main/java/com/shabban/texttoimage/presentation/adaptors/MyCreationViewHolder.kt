package com.shabban.texttoimage.presentation.adaptors

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.ImageLoader
import com.shabban.texttoimage.Common.loadImageFromEncodedStringAsync
import com.shabban.texttoimage.Data.room.ImageEntity
import com.shabban.texttoimage.databinding.MyCreationItemBinding

class MyCreationViewHolder(
    private val binding: MyCreationItemBinding
) : ViewHolder(binding.root) {

    fun bind(
        context: Context,
        item: ImageEntity
    ) {
        binding?.apply {
            /*val imageLoader = ImageLoader.Builder(context).build()
            binding?.progressBar?.let {progressBar->
                ivImage.loadImageWithProgress(item.imageUrl?:"",imageLoader,
                    progressBar,{
                    },{
                    })
            }*/
            item.imageBase64?.let {
                binding?.progressBar?.let { it1 ->
                    binding?.ivImage?.loadImageFromEncodedStringAsync(
                        it,
                        it1
                    )
                }
            }
        }

    }
}