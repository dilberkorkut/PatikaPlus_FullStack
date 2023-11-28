package dev.patika.library.business.abstracts;

import dev.patika.library.entities.Book;
import dev.patika.library.entities.BookBorrowing;

import java.util.List;

public interface IBookService {

    Book getById(int id);

    Book save(Book book);

    Book update(Book book);

    void delete(int id);

    List<Book> findAll();
}
