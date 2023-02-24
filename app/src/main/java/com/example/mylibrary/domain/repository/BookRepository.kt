package com.example.mylibrary.domain.repository

import androidx.lifecycle.LiveData
import com.example.mylibrary.domain.entity.Book

interface BookRepository {
    fun addBookItem(book: Book)
    fun editBookItem(book: Book)
    fun deleteBookItem(book: Book)
    fun getBookItem(bookId: Int): Book
    fun getBookList(): LiveData<List<Book>>
}