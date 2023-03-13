package com.example.mylibrary.domain.use_cases

import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository

class EditBookItemUseCase(
    private val repository: BookRepository
) {
    suspend fun editBookItem(book: Book){
        repository.editBookItem(book)
    }
}