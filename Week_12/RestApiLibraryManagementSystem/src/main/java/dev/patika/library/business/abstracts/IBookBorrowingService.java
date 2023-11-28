package dev.patika.library.business.abstracts;

import dev.patika.library.entities.BookBorrowing;
import dev.patika.library.entities.Publisher;

import java.util.List;


public interface IBookBorrowingService {

    BookBorrowing getById(int id);

    BookBorrowing save(BookBorrowing bookBorrowing);

    BookBorrowing update(BookBorrowing bookBorrowing);

    void delete(int id);

    List<BookBorrowing> findAll();
}
