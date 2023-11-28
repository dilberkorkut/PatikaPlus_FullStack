package dev.patika.library.dto.response;

import dev.patika.library.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingUpdateResponse {
    private int id;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Book book;

}
