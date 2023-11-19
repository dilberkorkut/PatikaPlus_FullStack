import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", columnDefinition = "serial")
    private int id;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Temporal(TemporalType.DATE)
    @Column(name = "author_birthDate")
    private LocalDate birthDate;

    @Column(name = "author_country")
    private String country;

    //bir yazarin birden fazla kitabi olabilir.
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE) // yazar tarafindan yapilan tum degisiklikler  otomatik olarak kitaplara da yansisin.
    private List<Book> bookList; // bir yazarin birden fazla kitabi olabilir. author_id, book_id'ler
    // TODO: 19.11.2023 CascadeType check! 

    public Author() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                '}';
    }
}
