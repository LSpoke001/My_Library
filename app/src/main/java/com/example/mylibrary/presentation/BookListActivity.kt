package com.example.mylibrary.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R

class BookListActivity : AppCompatActivity() {

    private lateinit var viewModel: BookListViewModel
    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[BookListViewModel::class.java]
        viewModel.bookList.observe(this){
            bookListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvAdapter = findViewById<RecyclerView>(R.id.rv_bookList)
        with(rvAdapter){
            bookListAdapter = BookListAdapter()
            adapter= bookListAdapter
        }
        setupClickListener()
        setupLongClickListener()
    }

    private fun setupLongClickListener() {
        bookListAdapter.onBookItemLongClickListener = {
            viewModel.changeEnabledBookItem(it)
        }
    }

    private fun setupClickListener() {
        bookListAdapter.onBookItemClickListener = {
            Log.d("BookItem", "${it.id} ${it.title} ${it.author}")
        }
    }
}