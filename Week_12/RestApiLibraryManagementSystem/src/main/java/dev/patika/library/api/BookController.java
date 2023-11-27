package dev.patika.library.api;


import dev.patika.library.business.abstracts.IBookService;
import dev.patika.library.dto.request.BookSaveRequest;
import dev.patika.library.dto.response.BookSaveResponse;
import dev.patika.library.entities.Book;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)

    public Book save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book newBook = this.modelMapper.map(bookSaveRequest, Book.class);
        return this.bookService.save(newBook);
    }
}

//        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
//        this.bookService.save(saveBook);
//        //dto kismini modelMapper ile otomatik yaptik.
//
//        // book responsa cevirmek icin
//        BookSaveResponse bookSaveResponse = this.modelMapper.forResponse().map(saveBook, BookSaveResponse.class);
//        return bookSaveResponse;

