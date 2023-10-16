public abstract class Product {

    private int id;
    private String name; //urun adi
    private double unitPrice;
    private String brandInfo; // sistemde ekli markalar kullanilacaktir.
    private int storage;
    private double screenSize;
    private int ram;
    private double discountRate;
    private int stockAmount;


    public Product(int id, String name, double unitPrice, double discountRate, int stockAmount, int storage, String brandInfo, double screenSize, int ram) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.discountRate = discountRate;
        this.stockAmount = stockAmount;
        this.storage = storage;
        this.name = name;
        this.brandInfo = brandInfo;
        this.screenSize = screenSize;
        this.ram = ram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int amountOfStock) {
        this.stockAmount = amountOfStock;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(String brandInfo) {
        this.brandInfo = brandInfo;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
