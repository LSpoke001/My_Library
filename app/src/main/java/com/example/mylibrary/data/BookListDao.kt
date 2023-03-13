package com.example.mylibrary.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookListDao {

    @Query("SELECT * FROM book_items")
    fun getBookList(): LiveData<List<BookItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookItem(bookItem: BookItemDbModel)

    @Query("DELETE FROM book_items WHERE id=:bookItemId")
    fun deleteBookItem(bookItemId: Int)

    @Query("SELECT * FROM book_items WHERE id=:bookItemId LIMIT 1")
    fun getBookItem(bookItemId: Int): BookItemDbModel
}