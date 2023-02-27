package com.example.mylibrary.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mylibrary.R
import com.example.mylibrary.domain.entity.Book

class AddAndEditScreenActivity : AppCompatActivity() {

    private var screenMode = UNKNOWN_MODE
    private var bookId = Book.UNIDENTIFIED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_and_edit_screen)
        parseIntent()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AddAndEditFragment())
            .commit()
    }
    private fun parseIntent(){
        if(!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("Extra screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if(mode != MODE_ADD && mode != MODE_EDIT){
            throw RuntimeException("Unknown mode $mode")
        }
        screenMode = mode
        if(screenMode == MODE_EDIT){
            if(!intent.hasExtra(EXTRA_BOOK_ITEM_ID)){
                throw RuntimeException("Unknown id absent")
            }
            bookId = intent.getIntExtra(EXTRA_BOOK_ITEM_ID, Book.UNIDENTIFIED_ID)
        }
    }

    companion object{
        private const val EXTRA_SCREEN_MODE ="screen_mode"
        private const val EXTRA_BOOK_ITEM_ID ="book_item_id"
        private const val MODE_ADD ="mode_add"
        private const val MODE_EDIT ="mode_edit"
        private const val UNKNOWN_MODE ="unknown_mode"

        fun newInstanceAddIntent(context: Context): Intent {
            val intent = Intent(context, AddAndEditScreenActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
        fun newInstanceEditIntent(context: Context, bookId: Int): Intent{
            val intent = Intent(context, AddAndEditScreenActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_BOOK_ITEM_ID, bookId)
            return intent
        }
    }
}