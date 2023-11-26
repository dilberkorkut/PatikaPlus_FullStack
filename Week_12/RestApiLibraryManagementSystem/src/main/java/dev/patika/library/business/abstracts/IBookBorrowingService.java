package dev.patika.library.business.abstracts;

import dev.patika.library.entities.BookBorrowing;

public interface IBookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);
}
