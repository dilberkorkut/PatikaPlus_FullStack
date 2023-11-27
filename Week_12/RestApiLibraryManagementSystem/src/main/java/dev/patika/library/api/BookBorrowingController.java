package dev.patika.library.api;

import dev.patika.library.business.abstracts.IBookBorrowingService;
import dev.patika.library.dto.request.BookBorrowingSaveRequest;
import dev.patika.library.entities.BookBorrowing;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrowings")
public class BookBorrowingController {

    private final IBookBorrowingService bookBorrowingService;
    private final ModelMapper modelMapper;

    public BookBorrowingController(IBookBorrowingService bookBorrowingService, ModelMapper modelMapper) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookBorrowing save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing newBookBorrowing = this.modelMapper.map(bookBorrowingSaveRequest, BookBorrowing.class);
        return this.bookBorrowingService.save(newBookBorrowing);
    }
}
