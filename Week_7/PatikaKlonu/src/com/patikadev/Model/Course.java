package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int user_id;
    private int patika_id; // patika id'lerden de patikayi cekmemiz gerek. patika nesnesi olusturmamiz gerek course icinde.
    private String name;
    private String lang;

    private Patika patika;
    private User educator;

    public Course(int id, int user_id, int patika_id, String name, String lang) { // constructora patika ve educatoru almadik
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;

        this.patika = Patika.getFetch(patika_id); // getFetch metoduna id verirsem patika nesnesini veriyor.
        this.educator = User.getFetch(user_id); //
    }

    public Course() {

    }



    public static ArrayList<Course> getList() {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj; // bo; bir obj olusturuyoruz

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course");
            while(rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id, user_id, patika_id, name, lang);
                courseList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;

    }

    public static boolean add(int user_id, int patika_id, String name, String lang) {
        String query = "INSERT INTO course (user_id, patika_id, name, lang) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, user_id);
            pr.setInt(2, patika_id);
            pr.setString(3, name);
            pr.setString(4, lang);
            return pr.executeUpdate() != -1 ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    public static ArrayList<Course> getListByUser(int user_id) { //kullaniciya ait kurslarin listesini ceker. id'ye gore listeyi arraylist ile dondurur.kurslar bu listede toplanir.
        ArrayList<Course> courseList = new ArrayList<>(); // Course sinifindan olusan kurslarin listesini saklamak icin bos bir ArrayList olusturur.
        Course obj; // bos bir obj olusturuyoruz. bu nesne her bir kurs kaydini temsil eder,
        try {
            Statement st = DBConnector.getInstance().createStatement(); //veri tabani sorgusu icin Statement nesnesi olusturulur. Veri tabanina baglanti icin DBConnector.getInstance ile baglanti alinir
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE user_id = " + user_id); //SQL sorgusu sonuclarini rs icinde toplar.
            while(rs.next()) { //her satir tek tek okunur.
                int id = rs.getInt("id");
               // int userID = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                // her bir kursun ozellikleri rs nesnesinden cikarilir ve yeni bir Course nesnesi olusturulur
                obj = new Course(id, user_id, patika_id, name, lang);
                courseList.add(obj); // olusturulan Course nesnesi coursListe(arraylist) eklenir. courseList dondurulur.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;

    }

    // bu course'a cevrilecek. course ismi kullanilarak course objesi alicaz.
    public static Course getFetch(String name){
        Course course = null; // eger sorgu sonucunda kayot bulunamazsa obj degiskeninin null olacagi anlamina gelir.
        String query = "SELECT * FROM course WHERE name = ?"; //SQL course tablosundan name sorgulama
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name); // hazirlanan SQL sorgusunda ? yerine name degerini parametre olarak ekler. bu sorgunun belirli bir uname icin calismasini saglar.
            ResultSet rs = pr.executeQuery(); // pr.executeQuery() ile sorgu calistirilir. sonuc rs degiskenine aktarilir.
            if (rs.next()){ // sorgu sonucunda bir sonraki satirin varligini kontrol eder.
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setUser_id(rs.getInt("user_id"));
                course.setPatika_id(rs.getInt("patika_id"));
                course.setName(rs.getString("name"));
                course.setLang(rs.getString("lang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course; // obj nesnesi doldurulduktan sonra bu nesne dondurulur. eger kayit bulunamazsa null dondurur.
    }


    public static boolean delete(int id) {
        String query = "DELETE FROM course WHERE id = ?";

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1 , id);

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
