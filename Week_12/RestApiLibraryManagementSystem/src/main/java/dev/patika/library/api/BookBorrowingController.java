package dev.patika.library.api;

import dev.patika.library.business.abstracts.IBookBorrowingService;
import dev.patika.library.dto.request.AuthorUpdateRequest;
import dev.patika.library.dto.request.BookBorrowingSaveRequest;
import dev.patika.library.dto.request.BookBorrowingUpdateRequest;
import dev.patika.library.entities.Author;
import dev.patika.library.entities.Book;
import dev.patika.library.entities.BookBorrowing;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/borrowings")
public class BookBorrowingController {

    private final IBookBorrowingService bookBorrowingService;
    private final ModelMapper modelMapper;

    public BookBorrowingController(IBookBorrowingService bookBorrowingService, ModelMapper modelMapper) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowing getById(@PathVariable("id") int id) {
        return this.bookBorrowingService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<BookBorrowing> findAll() {
        return this.bookBorrowingService.findAll();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookBorrowing save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing newBookBorrowing = this.modelMapper.map(bookBorrowingSaveRequest, BookBorrowing.class);

//        public @Valid BookBorrowingSaveRequest save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
//        bookBorrowingService.save(modelMapper.map(bookBorrowingSaveRequest,BookBorrowing.class));
//        return bookBorrowingSaveRequest;

//        Book book = new Book();
//        book.setId(bookBorrowingSaveRequest.getBorrow_book_id());
//        newBookBorrowing.setBook(book);

        return this.bookBorrowingService.save(newBookBorrowing);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowing update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing newBookBorrowing = this.bookBorrowingService.getById(bookBorrowingUpdateRequest.getId());
        newBookBorrowing.setBorrowerName(bookBorrowingUpdateRequest.getBorrowerName());
        newBookBorrowing.setBorrowingDate(bookBorrowingUpdateRequest.getBorrowingDate());
        newBookBorrowing.setReturnDate(bookBorrowingUpdateRequest.getReturnDate());
        return this.bookBorrowingService.update(newBookBorrowing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.bookBorrowingService.delete(id);

    }


}

