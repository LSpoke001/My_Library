package com.example.mylibrary.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R

class BookItemHolder(val view: View): RecyclerView.ViewHolder(view) {
    val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    val tvAuthor = view.findViewById<TextView>(R.id.tv_author)
}