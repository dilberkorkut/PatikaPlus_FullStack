package dev.patika.library.dto.request;

import dev.patika.library.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingSaveRequest {
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Book book;
}
