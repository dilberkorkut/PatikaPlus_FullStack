package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.IBookBorrowingService;
import dev.patika.library.dao.BookBorrowingRepo;
import dev.patika.library.entities.BookBorrowing;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final BookBorrowingRepo bookBorrowingRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
    }
}
