package com.example.mylibrary.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mylibrary.R
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
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return BookItemHolder(view)
    }

    override fun onBindViewHolder(holder: BookItemHolder, position: Int) {
        val item = getItem(position)
        holder.tvTitle.text = item.title
        holder.tvAuthor.text = item.author

        holder.view.setOnClickListener{
            onBookItemClickListener?.invoke(item)
        }
        holder.view.setOnLongClickListener {
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