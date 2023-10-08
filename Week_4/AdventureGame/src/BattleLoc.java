import java.sql.SQLOutput;
import java.util.Random;

public class BattleLoc extends Location {

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    public BattleLoc (Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        System.out.println("Suan buradasiniz : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yasiyor !");
        System.out.print("<S>avas veya <K>ac : ");
        String selectCase = input.nextLine().toUpperCase();
        if(selectCase.equals("S") && combat(obsNumber)) {
            // Savasma islemi yapilacak.
            System.out.println(this.getName() + " tum dusmanlari yendiniz!" );
            earnAward();
            System.out.println(award + "Kazandiniz");
           // addAward(); // odulleri ekleme metodu
            return true;

        }
        if(this.getPlayer().getHealth() <= 0) {
            System.out.println("Oldunuz!");
            return false;
        }
        return true;
    }

    public void earnAward() {
        if(this.award.equals("Food")) {
            this.getPlayer().getInventory().setFood(true);
        }
        if(this.award.equals("Firewood")) {
            this.getPlayer().getInventory().setFood(true);
        }
        if(this.award.equals("Water")) {
            this.getPlayer().getInventory().setFood(true);
        }
    }

    // kac canavar ile savasilacaksa o kadar dongu
    public boolean combat(int obsNumber) {

        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrijinalHealth());
            playerStats();
            obstacleStats(i);
            boolean firstStrike = randomAttack();  // ilk hamle random %50 sans! RandomAttack metodu asagidadir.
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                if(firstStrike){
                    System.out.print("<V>ur veya <K>ac :  ");
                    String selectCombat = input.nextLine().toUpperCase();

                    if(selectCombat.equals("V")) {

                        System.out.println("Siz vurdunuz!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                        if(this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(obstacleDamage < 0 ) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage );
                            afterHit();
                        }
                    } else {
                        return false;
                    }
                }
            }

            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Dusmani Yendiniz!");


                //Eğer savaşılan lokasyondaki canavarın ID'si 4(yılan)sa, getDropLoot() metodu çağırıldı
                if (this.getObstacle().getId() == 4) {

                    getDropLoot();   // rastgele esya kazanma metodu.
                }

                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Guncel Paraniz: " + this.getPlayer().getMoney());
            }else{
                return false;

            }
        }
        return false;
    }

    public boolean randomAttack() {
        double randNumber = Math.random() * 100;
        return (randNumber >= 50);
    }

    /* oduul yuzdeleri
       %15 silah              % 20 tufek % 30 kilic % 50 tabanca
       %15 zirh               % 20 hafif % 30 orta % 50 agir
       %25 para               % 20 -->10 para, %30 -->5 para,  %50 -->1 para
       %56 hicbir sey kazanamama
     */


    public void getDropLoot() {
        Random random = new Random();
        int lootChance = random.nextInt(101);
        if (lootChance >= 0 && lootChance <= 15) { // silahın yüzdesini hesaplıyoruz
            System.out.println("---Bir silah düştü!---");
            double gunChance = random.nextInt(101);

            if (gunChance >= 0 && gunChance <= 20) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
                System.out.println("Kazandığınız silah : " + this.getPlayer().getInventory().getWeapon().getName());
            } else if (gunChance > 20 && gunChance <= 50) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
                System.out.println("Kazandığınız silah : " + this.getPlayer().getInventory().getWeapon().getName());
            } else {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));
                System.out.println("Kazandığınız silah : " + this.getPlayer().getInventory().getWeapon().getName());
            }

        } else if (lootChance > 15 && lootChance <= 30) {
            // geri kalan 15 lik dilimi zırhın yüzdesi olarak hesaplıyoruz
            System.out.println("---Bir zırh düştü!---");
            double armorChance = random.nextInt(101);

            if (armorChance >= 0 && armorChance <= 20) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
                System.out.println("Kazandığınız zırh : " + this.getPlayer().getInventory().getWeapon().getName());
            } else if (armorChance >= 21 && armorChance <= 50) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
                System.out.println("Kazandığınız zırh : " + this.getPlayer().getInventory().getArmor().getName());
            } else {
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
                System.out.println("Kazandığınız zırh : " + this.getPlayer().getInventory().getArmor().getName());
            }

        } else if (lootChance > 30 && lootChance <= 55) {
            // para yüzdelerini hesaplıyoruz.
            System.out.println("---Para kazandınız---");
            double coinChance = random.nextInt(101);

            if (coinChance >= 0 && coinChance <= 20) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                System.out.println("Kazanılan para : 10 ");
            } else if (coinChance > 20 && coinChance <= 50) {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                System.out.println("Kazanılan para : 5");
            } else {
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                System.out.println("Kazanılan para : 1");
            }
        } else {//hiçbir şey kazanamama durumu.
            System.out.println("Hicbirsey Kazanamadiniz!");
        }
    }





    public void afterHit() {
        System.out.println("Caniniz : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Cani : " + this.getObstacle().getHealth());
        System.out.println("--------------------");
    }

    public void playerStats() {
        System.out.println("Oyuncu Degerleri");
        System.out.println("-----------------------");
        System.out.println("Saglik: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zirh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para:" + this.getPlayer().getMoney());
        System.out.println();

    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName()+ " Degerleri");
        System.out.println("-----------------------");
        System.out.println("Saglik: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Odul: " + this.getObstacle().getAward());
        System.out.println();

    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
        // 0+1,1+1 => 1,2
    }
    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
