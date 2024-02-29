package dev.patika.library.api;


import dev.patika.library.business.abstracts.IBookService;
import dev.patika.library.dto.request.AuthorUpdateRequest;
import dev.patika.library.dto.request.BookSaveRequest;
import dev.patika.library.dto.request.BookUpdateRequest;
import dev.patika.library.dto.response.BookSaveResponse;
import dev.patika.library.entities.Author;
import dev.patika.library.entities.Book;
import dev.patika.library.entities.BookBorrowing;
import dev.patika.library.entities.Publisher;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    //business katmanina ihtiyacimiz var.
    private final IBookService bookService;
    private final ModelMapper modelMapper;

    public BookController(IBookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getById(@PathVariable("id") int id) {
        return this.bookService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAll() {
        return this.bookService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book newBook = this.modelMapper.map(bookSaveRequest, Book.class);
//        Author author = new Author();
//        author.setId((long) bookSaveRequest.getAuthor_id());
//        newBook.setAuthor(author);
//        Publisher publisher =new Publisher();
//        publisher.setId((long) bookSaveRequest.getPublisher_id());
//        newBook.setPublisher(publisher);
        return this.bookService.save(newBook);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Book update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book newBook = this.bookService.getById(bookUpdateRequest.getId());
        newBook.setName(bookUpdateRequest.getName());
        newBook.setPublicationYear(bookUpdateRequest.getPublicationYear());
        newBook.setStock(bookUpdateRequest.getStock());
        return this.bookService.update(newBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
    }
}

//        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
//        this.bookService.save(saveBook);
//        //dto kismini modelMapper ile otomatik yaptik.
//
//        // book responsa cevirmek icin
//        BookSaveResponse bookSaveResponse = this.modelMapper.forResponse().map(saveBook, BookSaveResponse.class);
//        return bookSaveResponse;

