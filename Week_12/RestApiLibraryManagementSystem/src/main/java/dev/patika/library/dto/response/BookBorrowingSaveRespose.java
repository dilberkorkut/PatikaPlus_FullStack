package dev.patika.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingSaveRespose {
    private int borrower_id;
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
}
