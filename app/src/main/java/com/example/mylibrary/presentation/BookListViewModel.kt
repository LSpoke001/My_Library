package com.example.mylibrary.presentation

import androidx.lifecycle.ViewModel
import com.example.mylibrary.data.BookRepositoryImpl
import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.use_cases.DeleteBookItemUseCase
import com.example.mylibrary.domain.use_cases.EditBookItemUseCase
import com.example.mylibrary.domain.use_cases.GetBookListUseCase

class BookListViewModel: ViewModel() {
    private val repository = BookRepositoryImpl

    private val getBookListUseCase = GetBookListUseCase(repository)
    private val deleteBookItemUseCase = DeleteBookItemUseCase(repository)
    private val editBookItemUseCase = EditBookItemUseCase(repository)

    val bookList = getBookListUseCase.getBookList()

    fun deleteBookItem(book: Book){
        deleteBookItemUseCase.deleteBookItem(book)
    }
    fun changeEnabledBookItem(book: Book){
        val newItem = book.copy(enabled = !book.enabled)
        editBookItemUseCase.editBookItem(newItem)
    }
}