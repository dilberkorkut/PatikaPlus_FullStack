import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotebookOperations {

    public static Scanner input = new Scanner(System.in);
    static ArrayList<Notebook> notebooks = new ArrayList<>();

    static {
        notebooks.add(new Notebook(1, "HUAWEI Matebook 14", 7000, 0.5,
                5, 128, "Huawei", 14, 16));

        notebooks.add(new Notebook(2, "LENOVO V14 IGL", 1024, 0.5,
                5, 0, "Lenovo", 14, 8));

        notebooks.add(new Notebook(3, "ASUS Tuf Gaming", 8199.0, 0.5,
                5, 0, "Asus", 14, 16));

        //Notebooks n1 = new Notebook(1, "HUAWEI Matebook 14", 7000.0, 0.5, 5, 128, .....);
    }

    public static void notebookMenu() {
        boolean showNotenookMenu = true;
        while (showNotenookMenu) {
            System.out.println();
            System.out.println("Notebook Islemleri");
            System.out.println("---------------------------");
            System.out.println("1 - Notebooklari Listele");
            System.out.println("2 - Notebooklari filtrele");
            System.out.println("3 - Yeni Notebook Ekle");
            System.out.println("4 - Notebook Sil");
            System.out.println("5 - Cikis");
            System.out.print("Lutfen bir islem seciniz : ");

            int selectNotebookOp = input.nextInt();
            switch (selectNotebookOp) {
                case 1:
                    printNotebookList();
                    break;
                case 2:
                    filterNotebook();
                    break;
                case 3:
                    addNotebook();
                    break;
                case 4:
                    removeNotebook();
                    break;
                case 5:
                    showNotenookMenu = false;
                    break;
                default:
                    System.out.println("Gecersiz secim. Lutfen tekrar deneyiniz");
            }
        }
    }
    public static void printNotebookList() {
        System.out.println();
        System.out.println("Notebook Listesi");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("ID        |Urun Adi              |Fiyat        |Marka        |Depolama    |Ekran    |RAM");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Notebook n : notebooks) {
            System.out.println(" |" + n.getId() + " |" + n.getName() + "        |" + n.getBrandInfo() + " | " + n.getStorage() + " | " + n.getScreenSize() + " | " + n.getRam() + " |");
        }
    }

    public static void filterNotebook() {
        boolean isIdBrand = true;
        while (isIdBrand) {
            System.out.println();
            System.out.println("1 - ID'ye gore filtrele");
            System.out.println("2 - Markaya gore filtrele");
            System.out.println("3 - Notebook islemlerine geri donun");
            System.out.print("Lutfen bir islem seciniz : ");

            int selection = input.nextInt();
            input.nextLine();
            switch (selection) {
                case 1:
                    filterNotebooksById();
                    break;
                case 2:
                    filterNotebooksByBrand();
                    break;
                case 3:
                    notebookMenu();
                    break;
                default:
                    isIdBrand=false;
                    System.out.println("Gecersiz secim. Lutfen tekrar deneyiniz");
                    break;
            }
        }
    }

    public static void filterNotebooksById() {
        printNotebookList();

        System.out.println();
        System.out.print("Tercih ettiginiz ID : ");
        int filteredNid = input.nextInt() - 1;
        while (filteredNid < 0 || filteredNid >= notebooks.size()) {
            System.out.println("Gecersiz bir secim. Lutfen tekrar deneyiniz.");
            filteredNid = input.nextInt() - 1; // gecerli bir id alinca dongu sonlaniyor.
        }
        Notebook nb = notebooks.get(filteredNid); // Kullanicidan alinan id'deki telefonu yeni bir nesneye atadik. yazdirabilmek icin.
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" |" + nb.getId() + " |" + nb.getName() + "        |" + nb.getBrandInfo() + " | " + nb.getStorage() + " | " + nb.getScreenSize() + " | " + nb.getRam() + " |");
        System.out.println("-----------------------------------------------------------------------------------");

    }

    /*
       bu sekilde olur mu kontrol!
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" |" + notebooks.get(nb).getId() + " |" + notebooks.get(nb).getName() + "        |" + notebooks.get(nb).getBrandInfo() + " | " + notebooks.get(nb).getStorage() + " | " + notebooks.get(nb).getScreenSize() + " | " + notebooks.get(nb).getRam() + " |");
        S
   */

    public static void filterNotebooksByBrand () {
        printNotebookList();

        System.out.println();
        System.out.print("Tercih ettiginiz marka : ");

        String filteredNbrand = input.nextLine();
        int counter = 0;
        for (Notebook nbk : notebooks) {
            if (nbk.getBrandInfo().equalsIgnoreCase(filteredNbrand)) {
                System.out.println();
                System.out.println(" |" + nbk.getId() + " |" + nbk.getName() + "        |" + nbk.getBrandInfo() + " | " + nbk.getStorage() + " | " + nbk.getScreenSize() + " | " + nbk.getRam() + " |");
            } else {
                if(counter == 0) {
                    System.out.println("Gecersiz bir secim. Lutfen tekrar deneyiniz!");
                    counter ++;
                }
            }
        }
    }

    public static void addNotebook(){

        System.out.print("Urun ID : ");
        int id = input.nextInt();

        System.out.print("Urun Adi : ");
        String name = input.nextLine() + input.nextLine();

        System.out.print("Urun Fiyati : ");
        double unitPrice = input.nextDouble();

        System.out.print("Indirim Orani : ");
        double discountRate = input.nextDouble();

        System.out.print("Stock Sayisi : ");
        int stockAmount = input.nextInt();

        System.out.print("Depolama : ");
        int storage = input.nextInt();
        input.nextLine(); // bos satir okuma

        System.out.print("Marka : ");
        String brandInfo = input.nextLine();

        System.out.print("Ekran Boyutu : ");
        double screenSize = input.nextDouble();

        System.out.print("Ram : ");
        int ram = input.nextInt();

        System.out.print("Hafiza : ");
        int memory = input.nextInt();

        System.out.print("Pil Gucu : ");
        double batteryPower = input.nextDouble();
        input.nextLine();

        System.out.print("Renk : ");
        String color = input.nextLine();

        System.out.print("Camera : ");
        int camera = input.nextInt();


        int newId = 1;
        if (!notebooks.isEmpty()) {
            newId = notebooks.get(notebooks.size() - 1).getId() + 1;
        }

        notebooks.add(new Notebook(newId, name, unitPrice, discountRate, stockAmount,
                storage, brandInfo, screenSize, ram));
        printNotebookList();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static void removeNotebook() {
        printNotebookList();

        System.out.println("Silmek istediginiz urunu seciniz : ");
        int id = input.nextInt() -1;
        while(id < 0 || id > notebooks.size()) {
            System.out.println("Hatali id girdiniz. Lutfen tekrar deneyiniz.");
            id = input.nextInt() -1;
        }
        notebooks.remove(id);
        printNotebookList();
    }
}



