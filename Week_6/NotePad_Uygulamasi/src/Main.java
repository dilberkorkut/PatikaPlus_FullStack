import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("notlar.txt");

        Scanner sc = new Scanner(System.in);
        System.out.println("Bir metin giriniz: ");
        String text = sc.nextLine();


        try {
            FileWriter fw = new FileWriter(file , true); // true varolan metni silmez, ekler. false mevcut metni siler yenisini ekler.
            PrintWriter pw = new PrintWriter(fw);
            pw.println(text);
            pw.close();

            FileReader fr = new FileReader(file); // file nesnesi ile dosya okuma baslatilir.
            BufferedReader br = new BufferedReader(fr); // BufferedReader dosyayi satir satir okur.
            String readingText; // string turunde degisken olusturuldu.
            while((readingText = br.readLine()) != null) { // readline() fonksiyonu her satiri okur. dosya sonuna ulasinca null doner. dongu sona erer.
                System.out.println(readingText);
            }
            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}