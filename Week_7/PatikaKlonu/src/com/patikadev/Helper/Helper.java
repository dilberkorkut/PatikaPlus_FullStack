package com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper { // burada statment metodlari olacak. yardimci konulari alicaz buraya

    public static void setLayout(){ //temayi degistirecek layout metodu. uygulamanin gorunumunu ayarlar/"look and feel"
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { //UIManager sinifi kullanilarak gorunum ayarlari yapilir.
            if("`Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenterPoint(String eksen, Dimension size) { //bu metot pencerenin ekranin ortasina konumlandirilmasini saglar. 2parametre alir.
        int point;
        switch (eksen) {
            case "x" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().width = size.width) / 2; // Toolkit sinifi ile bazi islevlere erisim kolaylasir. orn.ekran ozellikleri icin getScreenSize
                break;
            case "y" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().height = size.height) / 2; //ortasina konumlamak icin gereken formul/
                break;
            default :
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field){ // JText field icerigini kontrol eder dolu mu bos mu? bossa true, degilse false
        return field.getText().trim().isEmpty(); // trim sagindaki solundaki bosluklari siler. isEmpty yerine "..trim().length == 0;" seklinde de yazilabilir
    }

    public static boolean isComboBoxEmpty(JComboBox cmb) {
        return cmb.getModel().getSelectedItem().equals("");
    }

    public static void showMsg(String str){
        optionPageTR();
        String msg;
        String title;

        switch(str) {
            case "fill":
                msg = "Lutfen tum alanlari doldurunuz!";
                title = "Hata!";
                break;
            case "done":
                msg = "Islem Basarili";
                title = "Sonuc";
                break;
            case "error":
                msg = "Bir hata olustu!";
                title = "Hata!";

            default :
                msg = str;
                title = "Mesaj";
        }

        JOptionPane.showMessageDialog(null , msg , title , JOptionPane.INFORMATION_MESSAGE );
        // JOptionePane ile bir iletisim penceresi olusturur ve mesaji gosterir.(onay kutusu,uyari iletileri...)
    }

    public static boolean confirm(String str) { //kullanicidan onay istemek icin kullanilir.
        String msg;
        optionPageTR();

        switch (str) {
            case "sure" :
                msg = "Bu islemi gerceklestirmek istediginize emin misiniz?";
                break;
            default:
                msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Son kararin mi?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageTR(){ //UIManager ile iletisim penceresini turkce olarak degistirir.
        UIManager.put("OptionPane.okButtonText" , "Tamam");
        UIManager.put("OptionPane.yesButtonText" , "Evet");
        UIManager.put("OptionPane.noButtonText" , "Hayir");

    }
}
