package com.example.mylibrary.domain.use_cases

import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository

class DeleteBookItemUseCase(
    private val repository: BookRepository
) {
    suspend fun deleteBookItem(book: Book){
        repository.deleteBookItem(book)
    }
}