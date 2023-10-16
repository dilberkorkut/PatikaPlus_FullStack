import java.util.*;

public class SmartphoneOperations {

    public static Scanner input = new Scanner(System.in);
    static ArrayList<Smartphone> smartphones = new ArrayList<>();

   // static Map<Integer, Smartphone> smartphones = new HashMap<>();

    static{
        smartphones.add(new Smartphone(1, "SAMSUNG GALAXY A51", 3199.0, 0.5,
                5, 128, "Samsung", 14, 8, 24,
                4000, "black", 3));

        smartphones.add(new Smartphone(2, "iPhone 11 64 GB ", 7379.0, 0.5,
                5, 128, "Apple", 14, 8, 24,
                4000, "pink", 1));

        smartphones.add(new Smartphone(3, "Redmi Note 10 Pro 8GB", 4300.0, 0.5,
                5, 128, "Xiaomi", 14, 8, 24,
                4000, "black", 1));

        //Smartphone s1 = new Smartphone(1, "SAMSUNG GALAXY A51", 3199.0, 30, 65,64, "Samsung);
    }

    public static void smartphoneMenu() {
        boolean showPhoneMenu = true;
        while(showPhoneMenu) {
            System.out.println();
            System.out.println("Telefon Islemleri");
            System.out.println("---------------------------");
            System.out.println("1 - Telefonlari Listele");
            System.out.println("2 - Telefonlari filtrele");
            System.out.println("3 - Yeni Telefon Ekle");
            System.out.println("4 - Telefon Sil");
            System.out.println("5 - Cikis");
            System.out.print("Lutfen bir islem seciniz : ");

            int selectPhoneOp = input.nextInt();
            switch (selectPhoneOp) {
                case 1 :
                    printSmartphoneList();
                    break;
                case 2:
                    filterSmartphones();
                    break;
                case 3 :
                    addSmartphone();
                    break;
                case 4:
                    removeSmartphone();
                    break;
                case 5 :
                    showPhoneMenu = false;
                    break;
                default:
                    System.out.println("Gecersiz secim. Lutfen tekrar deneyiniz");
            }
        }
    }

    public static void printSmartphoneList() {
        System.out.println();
        System.out.println("Telefon Listesi");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("ID | Urun Adi              |Fiyat        |Marka        |Depolama    |Ekran    |RAM");
        System.out.println("-----------------------------------------------------------------------------------");
        for(Smartphone sp : smartphones) {
            System.out.println(" | " + sp.getId() + " | " + sp.getName() + " |" + sp.getBrandInfo() + " | " + sp.getStorage() + " | " + sp.getScreenSize() + " | " + sp.getRam() + " |");
        }


       /* for (Smartphone sp : smartphones) {
            System.out.format("| %-3d | %-30s | %-10.2f TL | %-10s | %-10d | %-10.1f | %-10d | %-10d | %-10d | %-10s |\n",
                    sp.getId(), sp.getName(), sp.getUnitPrice(), sp.getBrandInfo(),
                    sp.getStorage(), sp.getScreenSize(), sp.getCamera(), sp.getBatteryPower(), sp.getRam(), sp.getColor());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
   */
    }

    public static void filterSmartphones() {
        boolean isPhoneIdBrand = true;
        while(isPhoneIdBrand) {
            System.out.println("1 - ID'ye gore filtrele");
            System.out.println("2 - Markaya gore filtrele");
            System.out.println("3 - Notebook islemlerine geri donun");
            System.out.print("Lutfen bir islem seciniz : ");

            int selection = input.nextInt();
            input.nextLine();
            switch(selection) {
                case 1 :
                    filterSmartphonesById();
                    break;
                case 2:
                    filterSmartphonesByBrand();
                    break;
                case 3:
                    smartphoneMenu();
                    break;
                default:
                    isPhoneIdBrand = false;
                    System.out.println("Gecersiz secim. Lutfen tekrar deneyiniz");
            }
        }
    }

    public static void filterSmartphonesById() {
        printSmartphoneList();
        System.out.println("Tercih ettiginiz ID : ");
        int filteredId = input.nextInt();
        while (filteredId < 0 || filteredId > smartphones.size()) {
            System.out.println("Gecersiz bir secim. Lutfen tekrar deneyiniz.");
            filteredId = input.nextInt(); // gecerli bir id alinca dongu sonlaniyor.
        }
        Smartphone ss = smartphones.get(filteredId); // Kullanicidan alinan id'deki telefonu yeni bir nesneye atadik. yazdirabilmek icin.
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(" |" + ss.getId() + " |" + ss.getName() + "        |" + ss.getBrandInfo() + " | " + ss.getStorage() + " | " + ss.getScreenSize() + " | " + ss.getRam() + " |");
        System.out.println("-----------------------------------------------------------------------------------");

    }

    public static void filterSmartphonesByBrand() {
        printSmartphoneList();
        System.out.println("Tercih ettiginiz marka : " );
        String filteredBrand = input.nextLine();
        int counterPh = 0;
        for(Smartphone ph : smartphones) {
            if(ph.getBrandInfo().toLowerCase().equals(filteredBrand.toLowerCase())){
                System.out.println();
                System.out.println(" |" + ph.getId() + " |" + ph.getName() + "        |" + ph.getBrandInfo() + " | " + ph.getStorage() + " | " + ph.getScreenSize() + " | " + ph.getRam() + " |");
            } else {
                if (counterPh == 0) {
                    System.out.println("Gecersiz bir secim. Lutfen tekrar deneyiniz!");
                    counterPh++;
                }
            }
        }
    }

    public static void addSmartphone() {

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
        if (!smartphones.isEmpty()) {
            newId = smartphones.get(smartphones.size() - 1).getId() + 1;
        }

        smartphones.add(new Smartphone(newId, name, unitPrice, discountRate, stockAmount,
                storage, brandInfo, screenSize, ram, memory, batteryPower, color, camera ));
        //Smartphone newSmartphone = new Smartphone(newId, name, unitPrice, discountRate,
               // stockAmount, memory, brandInfo, screenSize, ram, batteryPower, color, camera);
        printSmartphoneList();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static void removeSmartphone() {
        printSmartphoneList();

        System.out.println("Silmek istediginiz urunu seciniz : ");
        int id = input.nextInt() -1;
        while(id < 0 || id > smartphones.size()) {
            System.out.println("Hatali id girdiniz. Lutfen tekrar deneyiniz.");
            id = input.nextInt() -1;
        }
        smartphones.remove(id);
        printSmartphoneList();
    }
}


