import java.util.Comparator;

public class Book {

    private String name;
    private int pageCount;
    private String author;
    private String publishDate;

    /*Constructor metodu : Javada bir nesne oluşturulduğunda, o nesnenin başlangıç durumu
    (örneğin, değişkenlerinin ilk değerleri) bu metot aracılığıyla ayarlanır.
    Baslangic degerleri atanmis olur*/

    public Book(String name, int pageCount, String author, String publishDate) {
        this.name = name;
        this.pageCount = pageCount;
        this.author = author;
        this.publishDate = publishDate;
    }


    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
