import java.util.TreeSet;

public class Brand implements Comparable<Brand> {
    private int id;
    private String name;

    static TreeSet<Brand> brandTreeSet = new TreeSet<>(); // brandTreeSet nesnesi olusturduk/

    static {
        /* Satilan markalar belli. Hafizada olan markalar kullanilacagi icin statik kod bloklari
        ve static degisken yazdik. yeni bir notebook veya telefon eklemek istedigimizde markalar
        hazir ciksin. Java static gordugu kod bloklarini gorup calistirir.*/

        brandTreeSet.add(new Brand(1, "Samsung"));
        brandTreeSet.add(new Brand(2, "Lenovo"));
        brandTreeSet.add(new Brand(3, "Apple"));
        brandTreeSet.add(new Brand(4, "Huawei"));
        brandTreeSet.add(new Brand(5, "Casper"));
        brandTreeSet.add(new Brand(6, "Asus"));
        brandTreeSet.add(new Brand(7, "HP"));
        brandTreeSet.add(new Brand(8, "Xiaomi"));
        brandTreeSet.add(new Brand(9, "Monster"));
    }

    @Override
    public int compareTo(Brand o) {
        return (this.getName().compareTo(o.getName()));
    }

    public static void listBrands() {
        int id = 0;
        for( Brand b : brandTreeSet) {
            System.out.println(b.getName() );
            id ++;
        }

    }

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) { //kullanilmasina gerek kalmayacak.
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TreeSet<Brand> getBrandTreeSet() {
        return brandTreeSet;
    }

    public static void setBrandTreeSet(TreeSet<Brand> brandTreeSet) {
        Brand.brandTreeSet = brandTreeSet;
    }


}
