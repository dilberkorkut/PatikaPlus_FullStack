package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.IBookService;
import dev.patika.library.dao.BookRepo;
import dev.patika.library.entities.Book;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {
    private final BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }
}
