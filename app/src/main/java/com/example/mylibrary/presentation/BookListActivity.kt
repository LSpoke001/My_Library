package com.example.mylibrary.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BookListActivity : AppCompatActivity() {

    private lateinit var viewModel: BookListViewModel
    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var buttonAddBook: FloatingActionButton
    private var fragmentContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragment_container)

        buttonAddBook = findViewById(R.id.btn_add_book)

        buttonAddBook.setOnClickListener {
            if(isLandscape()) {
                launchFragment(AddAndEditFragment())
            }else{
                startActivity(AddAndEditScreenActivity.newInstanceAddIntent(this))
            }
        }

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[BookListViewModel::class.java]
        viewModel.bookList.observe(this){
            bookListAdapter.submitList(it)
        }
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        val rvBookList = findViewById<RecyclerView>(R.id.rv_bookList)
        with(rvBookList){
            bookListAdapter = BookListAdapter()
            adapter= bookListAdapter
        }
        setupClickListener()
        setupLongClickListener()
        setupSwipeBookItem(rvBookList)
    }

    private fun isLandscape(): Boolean{
        return fragmentContainer != null
    }

    private fun setupSwipeBookItem(rvBookList: RecyclerView?) {
        val callBack = object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = bookListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteBookItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvBookList)
    }

    private fun setupLongClickListener() {
        bookListAdapter.onBookItemLongClickListener = {
            viewModel.changeEnabledBookItem(it)
        }
    }

    private fun setupClickListener() {
        bookListAdapter.onBookItemClickListener = {
            if(isLandscape()){
                launchFragment(AddAndEditFragment())
            }else {
                startActivity(AddAndEditScreenActivity.newInstanceEditIntent(this, it.id))
            }
        }
    }
}