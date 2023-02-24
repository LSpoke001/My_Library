package com.example.mylibrary.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mylibrary.domain.entity.Book
import com.example.mylibrary.domain.repository.BookRepository
import kotlin.random.Random

class BookRepositoryImpl: BookRepository {

    private val bookList = sortedSetOf<Book>({o1, o2 -> o1.id.compareTo(o2.id)})
    private val bookListLD = MutableLiveData<List<Book>>()
    private var autoincrement = 0

    init{
        for(i in 0 until 10){
            val item = Book(
                "Title $i",
                "Author $i",
                "Description $i",
                enabled = Random.nextBoolean()
            )
            addBookItem(item)
        }
    }

    override fun addBookItem(book: Book) {
        if(book.id == Book.UNIDENTIFIED_ID){
            book.id = autoincrement++
        }
        bookList.add(book)
        updateBookList()
    }

    override fun editBookItem(book: Book) {
        val oldBook = getBookItem(book.id)
        bookList.remove(oldBook)
        addBookItem(book)
    }

    override fun deleteBookItem(book: Book) {
        bookList.remove(book)
        updateBookList()
    }

    override fun getBookItem(bookId: Int): Book {
        return bookList.find{
            it.id == bookId
        } ?: throw java.lang.RuntimeException(
            "Book with id $bookId not found"
        )
    }

    override fun getBookList(): LiveData<List<Book>> {
        return bookListLD
    }
    private fun updateBookList(){
        bookListLD.value = bookList.toList()
    }
}