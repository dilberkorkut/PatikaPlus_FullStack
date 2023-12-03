package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.IBookBorrowingService;
import dev.patika.library.dao.BookBorrowingRepo;
import dev.patika.library.entities.BookBorrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    @Autowired
    private final BookBorrowingRepo bookBorrowingRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
    }

    @Override
    public BookBorrowing getById(int id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow();
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {

        if (bookBorrowing.getBook().getStock() < 1) {
            throw new RuntimeException();
        } else {
            bookBorrowing.getBook().setStock(bookBorrowing.getBook().getStock() - 1);
            return this.bookBorrowingRepo.save(bookBorrowing);

        }
     //   return this.bookBorrowingRepo.save(bookBorrowing);
    }


    @Override

    public BookBorrowing update(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public void delete(int id) {
        this.bookBorrowingRepo.delete(this.getById(id));

    }

    @Override
    public List<BookBorrowing> findAll() {
        return this.bookBorrowingRepo.findAll();
    }
}
