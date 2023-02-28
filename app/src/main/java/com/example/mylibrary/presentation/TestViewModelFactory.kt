package com.example.mylibrary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TestViewModelFactory(
    private val title: String,
    private val author: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TestViewModel::class.java)){
            return TestViewModel(title, author) as T
        }
        throw RuntimeException("Unknown mode class $modelClass")
    }
}