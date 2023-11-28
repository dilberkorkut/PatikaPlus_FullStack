package dev.patika.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column (name = "borrower_id", insertable = false , columnDefinition = "serial")
    private Long id;

    @NotNull
    @Column (name = "borrower_name")
    private String borrowerName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column (name = "borrow_date")
    private LocalDate borrowingDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column (name = " return_date")
    private LocalDate returnDate;

    // bir kitabin birden fazla odunc alinma senaryosu olabilir.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrow_book_id", referencedColumnName = "book_id")
    @JsonIgnore
    private Book book;

}
