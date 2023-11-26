package dev.patika.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.ListTokenSource;

import java.util.List;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "serial")
    private int id;

    @NotNull
    @Column(name = "book_name")
    private String name;

    @NotNull
    @Column(name = "book_publication_year")
    private int publicationYear;

    @NotNull
    @Column(name = "book_stock")
    private int stock;

    // birden cok kitabin bir yazari olabilir. bire cok iliski. one-yazar, many-kitap tarafi.
    @ManyToOne(fetch = FetchType.EAGER) // eger gereksizse join sorgusu yapmamak ve gelecek veriyi kisitlamak icin fetch kullaniyorum. author id sini istedigimiz icin eager
    @JoinColumn(name = "book_author_id", referencedColumnName = "author_id" ) // eger yukarida book_id yerine id diye isimlendirseydik referans noktasina gerek kalmayacakti.
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;

    // bir kitap birden fazla kez odunc alinmis olabilir. odunc alinan listesi olusturuyoruz
    @OneToMany (mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BookBorrowing> bookBorrowingList;

    // Farkli bir referans tablosu olusuyor.

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book2category", // yeni olusacak tablo adi
            joinColumns = {@JoinColumn(name = "book2category_book_id")},
            inverseJoinColumns = {@JoinColumn (name = "book2category_category_id")})
    private List<Category> categoryList;




}
