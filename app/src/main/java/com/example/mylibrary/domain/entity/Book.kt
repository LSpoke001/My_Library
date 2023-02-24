package com.example.mylibrary.domain.entity

data class Book(
    val title:String,
    val author: String,
    val description: String,

    var enabled: Boolean = true,
    var imgUrl: String = UNIDENTIFIED_IMG,
    var id: Int = UNIDENTIFIED_ID
){
    companion object{
        const val UNIDENTIFIED_ID = -1
        const val UNIDENTIFIED_IMG = "UNIDENTIFIED_IMG"
    }
}
