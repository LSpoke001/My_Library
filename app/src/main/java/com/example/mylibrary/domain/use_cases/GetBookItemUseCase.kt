package com.example.mylibrary.domain.use_cases

import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository

class GetBookItemUseCase(
    private val repository: BookRepository
) {
    suspend fun getBookItem(bookId: Int): Book {
        return repository.getBookItem(bookId)
    }
}