package com.shabban.texttoimage.presentation.callback

import androidx.recyclerview.widget.DiffUtil
import com.shabban.texttoimage.Data.room.ImageEntity

object MyCreationAdapterDiffUtil : DiffUtil.ItemCallback<ImageEntity>() {
    override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem.id == newItem.id
                && oldItem.imageUrl == newItem.imageUrl

    }
}