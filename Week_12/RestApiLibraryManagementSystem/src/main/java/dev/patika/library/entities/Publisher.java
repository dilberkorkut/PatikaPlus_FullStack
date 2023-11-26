package dev.patika.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "publishers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", columnDefinition = "serial")
    private Long id;

    @Column(name = "publisher_name" , nullable = false)
    private String name;

    @Column(name = "publisher_establishment_year", nullable = false)
    private Integer establishmentYear;

    @Column(name = "publisher_address", nullable = false)
    private String address;

    // bir yayin evinin birden cok kitabi olabilir.
    //cascade : veri tabaninda bir yazara ait 5 kitap olsun. her iki tablo dolu.
    // eger bir yazara ait bir kitabi silersek ve ona ait 5 kitap anlamsiz olacak. cascade remove ile kitaplari da kaldirmis oluruz.
    //eger cascade.All dersek bir yazari guncelledigimde ona ait kitap verilerinin de guncellediggini anlamis oluruz.
    @OneToMany (mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Book> books;

}
