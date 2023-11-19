import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "serial")
    private int id;

    @Column(name = "book_name", nullable = false)
    private String name;


    @Column(name = "book_publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "book_stock", nullable = false)
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)  // bir kitabin bir yazari olabilir. bir yazarin birden fazla kitabi olabilir. author_id, book_id'ler
    @JoinColumn(name = "book_author_id", referencedColumnName = "author_id")
    private Author author;
    // TODO: 19.11.2023 check idler
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)  // bir kitap birden fazla kategoriye ait olabilir. Bir kategoriye birden fazla kitap dahil olabilir.
    @JoinTable(
            name = "book2category",
            joinColumns = {@JoinColumn(name = "book2category_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "book2category_category_id" )})
    private List<Category> categoryList;
    // TODO: 19.11.2023 check list

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_publisher_id",referencedColumnName = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<BookBorrowing> borrowingList;
    // TODO: 19.11.2023 cascade check! 




    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<BookBorrowing> getBorrowingList() {
        return borrowingList;
    }

    public void setBorrowingList(List<BookBorrowing> borrowingList) {
        this.borrowingList = borrowingList;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicationYear=" + publicationYear +
                ", stock=" + stock +
                ", author=" + author +
                ", categoryList=" + categoryList +
                ", publisher=" + publisher +
                ", borrowingList=" + borrowingList +
                '}';
    }
}
