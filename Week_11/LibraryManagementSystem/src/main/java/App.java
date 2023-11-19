import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
      //  entityManager.getTransaction().begin();

        //Yeni Publisher
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Can Yayinlari");
        publisher.setAddress("Istanbul");
        publisher.setEstablishmentYear(1981);
        entityManager.persist(publisher);

        //yeni category
        Category roman = new Category("roman");
        Category hikaye = new Category("hikaye");
        Category siir = new Category("siir");

        entityManager.persist(roman);
        entityManager.persist(hikaye);
        entityManager.persist(siir);


        //yeni bir yazar
        Author author = new Author();
        author.setAuthorName("Milan Kundera");
        author.setBirthDate(LocalDate.parse("1924-04-01"));
        author.setCountry("Çekya");
        entityManager.persist(author);

        //Yeni bir kitap
        Book book = new Book();
        book.setName("Yavaslik");
        book.setPublicationYear(1995);
        book.setStock(5);
        book.setPublisher(publisher);
        book.setAuthor(author);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(roman);
        categoryList.add(hikaye);
        book.setCategoryList(categoryList);

        entityManager.persist(book);

        // odunc alma islemi
        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setBook(book);
        bookBorrowing.setBorrowerName("Dilber Korkut Bilgin");
        bookBorrowing.setBorrowingDate(LocalDate.now());
        bookBorrowing.setReturnDate(LocalDate.now().plusDays(6)); // kitap 6 gun icine teslim edilecek.


        entityManager.persist(bookBorrowing);

//        Book book = entityManager.find(Book.class,1);
//        System.out.println(book.getCategoryList().toString());


        transaction.commit();

    }
}
