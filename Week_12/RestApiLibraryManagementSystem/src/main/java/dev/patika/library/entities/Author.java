package dev.patika.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", columnDefinition = "serial")
    private Long id;

    @NotNull
    @Column(name = "author_name")
    private String name;


    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "author_birthdate")
    private LocalDate birthDate;

    @NotNull
    @Column(name = "author_country")
    private String country;

    // bir yazarin bircok kitabi var. o halde kitap listesi tutabiliriz.
    //mappedBy = "author yazdik cunku Author sinifindan author nesnesi olusturduk.Author yazar deseydik yazar diyecektik.

    @OneToMany (mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Book> bookList;

}
