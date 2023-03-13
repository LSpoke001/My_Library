package com.example.mylibrary.domain.repository

import androidx.lifecycle.LiveData
import com.example.mylibrary.domain.entity.Book

interface BookRepository {
    suspend fun addBookItem(book: Book)
    suspend fun editBookItem(book: Book)
    suspend fun deleteBookItem(book: Book)
    suspend fun getBookItem(bookId: Int): Book
    fun getBookList(): LiveData<List<Book>>
}