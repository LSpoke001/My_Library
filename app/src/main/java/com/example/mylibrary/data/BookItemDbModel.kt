package com.example.mylibrary.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mylibrary.domain.entity.Book

@Entity(tableName = "book_items")
data class BookItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title:String,
    val author: String,
    val description: String,
    val enabled: Boolean
)
