package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Educator;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Student;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_uname;
    private JPasswordField fld_user_password;
    private JButton btn_login;

    public LoginGUI() { // bu constructor metodunda GUI bilesenleri olusturulur ve pencere ayarlari yapilir.Boyutu, konumu,basligi,gorunurlugu..
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_login.addActionListener(e -> { // logine basinca calisacak kodlar burada tanimlanir.
            if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_password)) { // kullanici adi sifre alanlari bos ise;
                Helper.showMsg("fill"); // Helper sinifindaki showMsg() fonksiyonunu cagir.
            } else {
                User u = User.getFetch(fld_user_uname.getText(), fld_user_password.getText());// eger metin alanlari doluysa kullanici nesnesi alinir.User,getFetch
                if (u == null) {
                    Helper.showMsg("Kullanici Bulunamadi!");
                } else { //Kullanici bulunursa type'a gore ekrana yonlendirme yapilir
                    switch (u.getType()) {
                        case "operator" :
                            OperatorGUI opGUI = new OperatorGUI(new Operator(u.getId(),u.getName(),u.getUname(),u.getPassword(),u.getType()));
                            //OperatorGUI opGUI = new OperatorGUI(new Operator((Operator) u); // bu sekilde yazinca hata veriyor.giris yapilamiyor operator icin.
                            break;
                        case "educator" :
                            EducatorGUI edGUI = new EducatorGUI(new Educator(u.getId(), u.getName(), u.getUname(), u.getPassword(), u.getType()));
                            break;
                        case "student" :
                            StudentGUI stGUI = new StudentGUI(new Student(u.getId(), u.getName(), u.getUname(), u.getPassword(), u.getType()));

                            break;
                    }
                    dispose(); // pencere kapatilir.
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI login = new LoginGUI();
    }
}
