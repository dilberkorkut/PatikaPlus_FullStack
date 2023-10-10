import java.util.Scanner;

public class Main {

    public static void checkArray(int [] arr, int index) throws Exception {
        if(index < 0 || index > arr.length ) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            System.out.println("Gecerli bir secim");
        }
    }

    public static void main(String[] args) {
        //10 elemanli dizi "arr"
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Scanner sc = new Scanner(System.in);
        System.out.print("Lutfen bir index giriniz: ");
        int index = sc.nextInt(); // secilen index "index"

        try {
            checkArray(arr, index);
        } catch (Exception e) {
            System.out.println("Gecersiz bir secim");
        }
    }
}