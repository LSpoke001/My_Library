package com.example.mylibrary.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.mylibrary.domain.entity.Book

class BookItemDiffCallBack: DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}