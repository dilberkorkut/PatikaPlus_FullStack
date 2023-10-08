import java.util.Random;

public class Snake extends Obstacle {
    public Snake() {
        super(4, "Yılan", randomDamage(), 12, 0);
    }

    private static int randomDamage() {
        return (int) (Math.random() * 4) + 3;
        /*Math.random() ile 0 ile 1 arasinda rastgele bir ondalik sayi elde eder.
          Bu sayiyi 4 ile carparak (3 ila 6 arasindaki tam sayilari elde etmek için)
           yilanin hasarini üretiriz.*/
    }

}