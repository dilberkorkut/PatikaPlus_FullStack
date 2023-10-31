package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;

public class SignUpGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_signup_uname;
    private JPasswordField fld_signup_password;
    private JTextField fld_signup_fullname;
    private JTextField fld_signup_email;
    private JButton btn_signup;

    public SignUpGUI() {
        add(wrapper);
        setSize(400,500);
        setLocation(Helper.screenCenterPoint("x", getSize()) , Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_signup.addActionListener(e -> {

            if(Helper.isFieldEmpty(fld_signup_uname)|| Helper.isFieldEmpty(fld_signup_password)|| Helper.isFieldEmpty(fld_signup_fullname))  {
                Helper.showMsg("fill");
            }else{
                User u = User.getFetch(fld_signup_uname.getText());
                if (u != null) {
                    Helper.showMsg("Kullanici adi mevcut. Yeni bir kullanici adi giriniz.");
                } else {
                    if (User.add(fld_signup_fullname.getText(), fld_signup_uname.getText(), fld_signup_password.getText(),"student")){
                        Helper.showMsg("done");
                        dispose();
                        LoginGUI loginGUI = new LoginGUI(); // sign up kapandiktan sonra login ekrani acilacak.
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        SignUpGUI signUp = new SignUpGUI();

    }




}
