package com.example.mylibrary.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository

class BookRepositoryImpl(
    application: Application
): BookRepository {

    private val bookListDao = AppDatabase.getInstance(application).bookListDao()
    private val mapper = BookListMapper()

    override suspend fun addBookItem(book: Book) {
        bookListDao.addBookItem(mapper.mapEntityToDbModel(book))
    }

    override suspend fun editBookItem(book: Book) {
        bookListDao.addBookItem(mapper.mapEntityToDbModel(book))
    }

    override suspend fun deleteBookItem(book: Book) {
        bookListDao.deleteBookItem(book.id)
    }

    override suspend fun getBookItem(bookId: Int): Book {
        val bookItemDb = bookListDao.getBookItem(bookId)
        return mapper.mapDbModelToEntity(bookItemDb)
    }


    override fun getBookList(): LiveData<List<Book>> = Transformations.map(
        bookListDao.getBookList()
    ){
        mapper.mapListDbModelToListEntity(it)
    }

}