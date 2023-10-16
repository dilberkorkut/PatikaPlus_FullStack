import java.util.Scanner;

public class PatikaStore {

    static Scanner input = new Scanner(System.in);

    public static void printMenu() {

        NotebookOperations notebookOperations = new NotebookOperations();

        boolean showStoreMenu = true; // while(true) seklinde yapinca 4-cikis islemi sonrasi tekrar tum menu karsima cikiyordum
                                      // boolean showStoreMenu=true tanimlayinca ve case cikis islemine boolean showStoreMenu diyince bir daha gostermedi.
        System.out.println();
        System.out.println("Patika Store'a Hosgeldiniz!");
        while(showStoreMenu) {

            System.out.println();
            System.out.println("Patika Store Urun Yonetim Paneli");
            System.out.println("1 - Notebook Islemleri");
            System.out.println("2 - Cep Telefonu Islemleri");
            System.out.println("3 - Marka Listele");
            System.out.println("4 - Cikis");
            System.out.print("Lutfen bir islem seciniz : ");

            int select = input.nextInt();

            switch(select) {
                case 1 :
                    NotebookOperations.notebookMenu();
                    break;
                case 2:
                    SmartphoneOperations.smartphoneMenu();
                    break;
                case 3:
                    System.out.println("Markalar");
                    Brand.listBrands();
                    break;
                case 4 :
                    showStoreMenu = false;
                    System.out.println();
                    System.out.println("Iyi gunler dileriz!");
                    break;
            }
        }
    }
}
