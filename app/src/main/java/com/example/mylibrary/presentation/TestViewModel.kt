package com.example.mylibrary.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestViewModel(
    private val title: String,
    private val author: String
): ViewModel() {

    private val _title = MutableLiveData<String>()
    val titleLD: LiveData<String>
        get() = _title

    private val _author = MutableLiveData<String>()
    val authorLD: LiveData<String>
        get() = _author

    fun getTextViewItems(){
        _title.value = title + " на ветвях"
        _author.value = author + " А.С."
    }
}