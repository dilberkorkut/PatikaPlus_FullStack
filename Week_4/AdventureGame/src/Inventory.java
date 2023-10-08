public class Inventory {
    private Weapon weapon;
    private Armor armor;

    //varsayilan false oyun kazanilinca true olacak.
    private boolean food = false;
    private boolean firewood = false;
    private boolean water = false;


    public Inventory() {
        this.weapon = new Weapon("Yumruk" , -1, 0, 0);
        this.armor = new Armor(-1, "Pacavra", 0, 0 );
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }
}