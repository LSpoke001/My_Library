package com.example.mylibrary.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository

class GetBookListUseCase(
    private val repository: BookRepository
) {
    fun getBookList(): LiveData<List<Book>>{
        return repository.getBookList()
    }
}