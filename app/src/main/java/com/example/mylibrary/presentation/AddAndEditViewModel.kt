package com.example.mylibrary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylibrary.data.BookRepositoryImpl
import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.use_cases.AddBookItemUseCase
import com.example.mylibrary.domain.use_cases.EditBookItemUseCase
import com.example.mylibrary.domain.use_cases.GetBookItemUseCase

class AddAndEditViewModel: ViewModel() {
    private val repository = BookRepositoryImpl

    private val getBookItemUseCase = GetBookItemUseCase(repository)
    private val addBookItemUseCase = AddBookItemUseCase(repository)
    private val editBookItemUseCase = EditBookItemUseCase(repository)

    private val _bookItem = MutableLiveData<Book>()
    val bookItem: LiveData<Book>
        get() = _bookItem

    private val _errorInputTitle = MutableLiveData<Boolean>()
    val errorInputTitle: LiveData<Boolean>
        get() = _errorInputTitle

    private val _errorInputAuthor = MutableLiveData<Boolean>()
    val errorInputAuthor: LiveData<Boolean>
        get() = _errorInputAuthor

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getBookItem(bookId: Int) {
        val item = getBookItemUseCase.getBookItem(bookId)
        _bookItem.value = item
    }

    fun addBookItem(titleInput:String?, authorInput: String?){
        val title = parseInput(titleInput)
        val author = parseInput(authorInput)
        val validInput = checkValidValue(title, author)
        if(validInput){
            val item = Book(title,author, enabled = true)
            addBookItemUseCase.addBookItem(item)
            finishWork()
        }
    }

    fun editBookItem(titleInput:String?, authorInput: String?){
        val title = parseInput(titleInput)
        val author = parseInput(authorInput)
        val validInput = checkValidValue(title, author)
        if(validInput){
            _bookItem.value?.let {
                val item = it.copy(title = title, author = author)
                editBookItemUseCase.editBookItem(item)
            }
            finishWork()
        }
    }

    private fun parseInput(input: String?): String{
        return input?.trim() ?: " "
    }
    private fun checkValidValue(title: String, author: String): Boolean{
        var result = true
        if(title.isBlank()){
            _errorInputTitle.value = true
            result = false
        }
        if(author.isBlank()){
            _errorInputAuthor.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputTitle(){
        _errorInputTitle.value = false
    }
    fun resetErrorInputAuthor(){
        _errorInputAuthor.value = false
    }
    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}