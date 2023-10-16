import java.util.Scanner;

public class PatikaStore {

    static Scanner input = new Scanner(System.in);

    public static void printMenu() {

        NotebookOperations notebookOperations = new NotebookOperations();

        while(true) {
            System.out.println("Patika Store'a Hosgeldiniz!");
            System.out.println("Patika Store Urun Yonetim Paneli! Ne yapmak istersiniz?");
            System.out.println("1 - Notebook Islemleri");
            System.out.println("2 - Cep Telefonu Islemleri");
            System.out.println("3 - Marka Listele");
            System.out.println("0 - Cikis");
            System.out.println();
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
                    System.out.println("Iyi gunler!");
                    break;
            }
        }
    }
}
