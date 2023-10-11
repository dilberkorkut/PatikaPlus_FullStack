import java.security.Key;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Lutfen bir metin giriniz:  ");
        String text = sc.nextLine();

        String [] words = text.split(" ");

        HashMap<String, Integer> wordCount = new HashMap<String,Integer>();

        for (String word : words) {
            if(wordCount.containsKey(word)){
                wordCount.put(word, wordCount.get(word)+1);
            } else {
                wordCount.put(word, 1);
            }
        }

        String mostUsedWord = "";
        int maxWordcount = 0;

        for(String word : words) {

            if(wordCount.get(word) > maxWordcount) {
                mostUsedWord = word;
                maxWordcount = wordCount.get(word);
            }
        }
        System.out.println();
        System.out.println("En cok kullanilan kelime: " + mostUsedWord +
                " ve " + " bu kelime " + maxWordcount + " kez kulanildi");



    }
}