import com.sun.source.doctree.SeeTree;

import java.util.TreeSet;

public  class Main {
    public static void main(String[] args) {

        TreeSet<Book> books = new TreeSet<>(new OrderNameComparator());

        books.add(new Book("Puslu Kitalar Atlasi",238, "Ihsan Oktay Anar", "1995"));
        books.add(new Book("Mutfak Sirlari",340, "Anthony Bourdain", "Agustos 2018"));
        books.add(new Book("Orlando",257, "Virginia Woolf", "Eylul 2014"));
        books.add(new Book("Raoul Taburin",103, "Sempe", "Ekim 2022"));
        books.add(new Book("Kaybolan O Gunler",192, "Timothe Le Boucher", "Ocak 2019"));

        for(Book book : books) {
            System.out.println(book.getName());
        }
        System.out.println("------------");

        for (Book book : books) {
            System.out.println(book.getPageCount());
        }
    }
}