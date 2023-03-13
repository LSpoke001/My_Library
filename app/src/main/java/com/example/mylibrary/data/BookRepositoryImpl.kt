package com.example.mylibrary.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mylibrary.R
import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository
import kotlin.random.Random

class BookRepositoryImpl(
    application: Application
): BookRepository {

    private val bookListDao = AppDatabase.getInstance(application).bookListDao()
    private val bookListMapper = BookListMapper()

    override fun addBookItem(book: Book) {
        bookListDao.addBookItem(bookListMapper.mapEntityToDbModel(book))
    }

    override fun editBookItem(book: Book) {
        bookListDao.addBookItem(bookListMapper.mapEntityToDbModel(book))
    }

    override fun deleteBookItem(book: Book) {
        bookListDao.deleteBookItem(book.id)
    }

    override fun getBookItem(bookId: Int): Book {
        val bookItemDb = bookListDao.getBookItem(bookId)
        return bookListMapper.mapDbModelToEntity(bookItemDb)
    }

    override fun getBookList(): LiveData<List<Book>> {
        TODO("Not yet implemented")
    }

    //  override fun getBookList(): LiveData<List<Book>> = bookListDao.getBookList()

}