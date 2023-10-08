import java.net.ServerSocket;

public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Magaza");
    }

    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("--------- Magazaya Hosgeldiniz ---------");
            System.out.println("1 - Silahlar ");
            System.out.println("2 - Zirhlar ");
            System.out.println("3 - Cikis Yap ");
            System.out.print(" Seciminiz: ");
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                selectCase = input.nextInt();
            }

            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                case 3:
                    System.out.println("Bir daha bekleriz.");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("--------- Silahlar ---------");
        for ( Weapon w : Weapon.weapons()) {
           System.out.println(w.getId() + " - " +  w.getName() + " < Para : " + w.getPrice() + " , Hasar : " + w.getDamage() + " >" );
       }
        System.out.println("0 - Cikis Yap");

    }

    public void buyWeapon() {// Satin almanin gerceklestigi alan

        System.out.print("Bir silah seciniz : ");

        int selectWeaponId = input.nextInt();
        while(selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length) {
            System.out.print("Gecersiz bir deger, tekrar giriniz: ");
            selectWeaponId = input.nextInt();
        }
        if(selectWeaponId != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);

            if(selectedWeapon != null) {
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paraniz bulunmamaktadir.");
                } else {
                    System.out.println(selectedWeapon.getName() + " silahini satin aldiniz!");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiye: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }


    }

    public void printArmor() {
        System.out.println("--------- Zirhlar ---------");
        for(Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() +
                    "<Para: " + a.getPrice() + " , Zirh: " + a.getBlock() + " >");
        }
        System.out.println("0 - Cikis Yap");
    }


    public void buyArmor() {
        System.out.print("Bir zirh seciniz: " );

        int selectArmorId = input.nextInt();
        while(selectArmorId < 1 || selectArmorId > Armor.armors().length) {
            System.out.println("Gecersiz bir deger, tekrar giriniz");
            selectArmorId = input.nextInt();
        }

       if(selectArmorId != 0) {
           Armor selectedArmor = Armor.getArmorObjById(selectArmorId);
           if(selectedArmor != null){
               if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                   System.out.println("Yeterli paraniz bulunmamaktadir!");
               } else {
                   System.out.println(selectedArmor.getName() + " zirhini satin aldiniz");
                   this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                   this.getPlayer().getInventory().setArmor(selectedArmor);
                   System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
               }
           }
       }
    }
}