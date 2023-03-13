package com.example.mylibrary.domain.entity

import com.example.mylibrary.R

data class Book(
    val title:String,
    val author: String,
    var description: String = DEFAULT_DESCRIPTION,
    var enabled: Boolean = true,
    var id: Int = UNIDENTIFIED_ID
){
    companion object{
        const val UNIDENTIFIED_ID = 0
        const val DEFAULT_DESCRIPTION = "Description"
    }
}
