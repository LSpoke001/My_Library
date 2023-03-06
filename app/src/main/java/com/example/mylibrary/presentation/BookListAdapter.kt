package com.example.mylibrary.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ItemDisabledBinding
import com.example.mylibrary.databinding.ItemDisabledBindingImpl
import com.example.mylibrary.databinding.ItemEnabledBinding
import com.example.mylibrary.domain.entity.Book

class BookListAdapter: ListAdapter<Book, BookItemHolder>(
    BookItemDiffCallBack()
) {

    var onBookItemLongClickListener: ((Book) -> Unit)? = null
    var onBookItemClickListener: ((Book) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemHolder {
        val layout = when(viewType){
            DISABLED_ITEM -> R.layout.item_disabled
            ENABLED_ITEM -> R.layout.item_enabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return BookItemHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding

        when(binding){
            is ItemDisabledBinding ->{
                binding.tvTitle.text = item.title
                binding.tvAuthor.text = item.author
            }
            is ItemEnabledBinding -> {
                binding.tvTitle.text = item.title
                binding.tvAuthor.text = item.author
            }
        }


        binding.root.setOnClickListener{
            onBookItemClickListener?.invoke(item)
        }
        binding.root.setOnLongClickListener {
            onBookItemLongClickListener?.invoke(item)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).enabled){
            ENABLED_ITEM
        }else{
            DISABLED_ITEM
        }

    }

    companion object{
        private const val DISABLED_ITEM = -1
        private const val ENABLED_ITEM = 1
    }
}