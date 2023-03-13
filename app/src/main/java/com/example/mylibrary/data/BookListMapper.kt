package com.example.mylibrary.data

import com.example.mylibrary.domain.entity.Book

class BookListMapper {

    fun mapEntityToDbModel(book: Book) = BookItemDbModel(
        id = book.id,
        title = book.title,
        author = book.author,
        description = book.description,
        enabled = book.enabled
    )
    fun mapDbModelToEntity(bookItemDbModel: BookItemDbModel) = Book(
        id = bookItemDbModel.id,
        title = bookItemDbModel.title,
        author = bookItemDbModel.author,
        description = bookItemDbModel.description,
        enabled = bookItemDbModel.enabled
    )

    fun mapListDbModelToListEntity(list: List<BookItemDbModel>) = list.map{
        mapDbModelToEntity(it)
    }
}