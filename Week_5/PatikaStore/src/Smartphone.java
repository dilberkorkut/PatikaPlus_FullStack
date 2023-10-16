public class Smartphone extends Product{

    private int memory;
    private double batteryPower;
    private String color;
    private int camera;

    public Smartphone(int id, String name, double unitPrice, double discountRate, int stockAmount,
                      int storage, String brandInfo, double screenSize, int ram, int memory,
                      double batteryPower, String color, int camera) {
        super(id, name, unitPrice, discountRate, stockAmount, storage, brandInfo, screenSize, ram);
        this.memory = memory;
        this.batteryPower = batteryPower;
        this.color = color;
        this.camera = camera;
    }


    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(double batteryPower) {
        this.batteryPower = batteryPower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }
}
