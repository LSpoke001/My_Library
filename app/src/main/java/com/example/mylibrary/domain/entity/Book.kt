package com.example.mylibrary.domain.entity

import com.example.mylibrary.R

data class Book(
    val title:String,
    val author: String,

    var description: String = DEFAULT_DESCRIPTION,
    var enabled: Boolean = true,
    var imgUrl: Int = UNIDENTIFIED_IMG,
    var id: Int = UNIDENTIFIED_ID
){
    companion object{
        const val UNIDENTIFIED_ID = -1
        const val UNIDENTIFIED_IMG = R.drawable.add_image
        const val DEFAULT_DESCRIPTION = "Description"
    }
}
