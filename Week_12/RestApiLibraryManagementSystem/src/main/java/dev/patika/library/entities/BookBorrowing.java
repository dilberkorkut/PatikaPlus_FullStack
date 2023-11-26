package dev.patika.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "book_borrowing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "borrower_id", columnDefinition = "serial")
    private Long id;

    @Column (name = "borrower_name", nullable = false)
    private String borrowerName;

    @Column (name = "borrow_date", nullable = false)
    private LocalDate borrowingDate;

    @Column (name = " return_date", nullable = false)
    private LocalDate returnDate;

    // bir kitabin birden fazla odunc alinma senaryosu olabilir.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrow_book_id", referencedColumnName = "book_id")
    private Book book;




}
