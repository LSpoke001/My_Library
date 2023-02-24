package com.example.mylibrary.domain.use_cases

import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository

class AddBookItemUseCase(
    private val repository: BookRepository
) {
    fun addBookItem(book: Book){
        repository.addBookItem(book)
    }
}