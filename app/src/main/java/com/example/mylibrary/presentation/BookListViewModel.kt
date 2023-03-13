package com.example.mylibrary.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mylibrary.data.BookRepositoryImpl
import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.use_cases.DeleteBookItemUseCase
import com.example.mylibrary.domain.use_cases.EditBookItemUseCase
import com.example.mylibrary.domain.use_cases.GetBookListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BookListViewModel(application: Application): AndroidViewModel(application) {
    private val repository = BookRepositoryImpl(application)

    private val getBookListUseCase = GetBookListUseCase(repository)
    private val deleteBookItemUseCase = DeleteBookItemUseCase(repository)
    private val editBookItemUseCase = EditBookItemUseCase(repository)

    val bookList = getBookListUseCase.getBookList()
    private val scope = CoroutineScope(Dispatchers.IO)

    fun deleteBookItem(book: Book){
        scope.launch {
            deleteBookItemUseCase.deleteBookItem(book)
        }
    }
    fun changeEnabledBookItem(book: Book){
        scope.launch {
            val newItem = book.copy(enabled = !book.enabled)
            editBookItemUseCase.editBookItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}