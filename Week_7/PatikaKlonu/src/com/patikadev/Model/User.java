package com.patikadev.Model;

// veri tabanindan aldigimiz herseyi burada nesnelestirmis oluyoruz.
// o yuzden bu bir model bizim icin. Model icinde User adinda bir yapi olusturduk.

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User { // veri tabanindan aldigimiz tum degerleri nesnelestirmis oluyoruz.
    private int id;
    private String name;
    private String uname;
    private String password;
    private String type;

    public User() {
    }
    //kurucu metotlar bir siniftan bir nesne olusturuldugunda otomatik olarak cagrilir.
    //nesnenin baslangic durumunu ayarlamak icin kullanilir.
    //geriye deger dondurmez(void) amaclari bir nesneyi baslatmaktir. kurucu metottan bir deger beklenmez.
    //alt siniflarin kullanimi kolaylasir

    public User(int id, String name, String uname, String password, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.type = type;
    }



    public static ArrayList<User> getList(){ // veritabanindaki tum kullanici verilerini ArrayList icinde dondurur.
        ArrayList<User> userList = new ArrayList<>(); // User turunden bir ArrayList olan userList olusturulur.
        String query = "SELECT * FROM user"; // tum user verileri alinir.
        User obj; // Her bir kullanici verisi bir User nesnesine donusturulur ve userlist listesine eklenir.
        // while icinde her defasinda yeni degerler atiyoruz.
        try {
            //veri tabanina baglanmadan once sorgu olusturuyoruz. connection statementlarini cagiriyoruz.
            // connectori static metotla aliyoruz.
            Statement st = DBConnector.getInstance().createStatement(); //getIstance metodu veri tabanina baglanti icin cagiriyoruz.
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){ // iterator mantigiyla aliyoruz/
                //her defasinda nesne uretmeyecegiz while icinde. varsayilan nesneyi degistiriyoruz. yukarda User obj; diye nesne uretiyoruz
                obj = new User(); // bos bir user olusturacagiz/ Asagiya degerleri aliyoruz. Userin constructoruna bakiyoruz ve o degerleri aliyoruz asagida.
                obj.setId(rs.getInt("id")); // rs veri tanbanindaki id'yi getiriyor. biz de obje uzerindeki id'yi set ile degistiryoruz.
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
                userList.add(obj); // olusturdugumuz objeyi userList arraylistimize ekliyoruz.
            }
            st.close();
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList; //son olarak userList dondurulur.
    }

    // add metodu yeni bir kullanici eklemek icin kullanilir. 'uname'.
    //geriye bir boolean degeri dondurmemiz gerekiyor. ekleme islemi gerceklesti mi gerceklesmedi mi
    //ayni kullanici adina sahip kullanici varsa eklemeyi reddeder.

    public static boolean add(String name, String uname, String password, String type ){
        String query = "INSERT INTO user (name, uname, password, type) VALUES (?,?,?,?)"; //query adinda sql sorgusu olusturuyoruz/?=>preparedStatement
        User findUser = User.getFetch(uname); //User.getFetch(uname) ile veri tabaninda daha once ayni uname ile kayitli bir user olup olmadigini kontrol edilir.
        if(findUser != null) { // eger ayni kullanici adi varsa hata mesaji alir.
            Helper.showMsg("Bu kullanici adi daha once eklenmistir. Lutfen farkli bir kullanici adi giriniz.");
            return false; // ve false dondurulur.
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, password);
            pr.setString(4, type);

            int response = pr.executeUpdate(); // bu response degeri sql sorgusu tarafindan etkilenen satir sayisi. kac satir eklendi,guncellendi,silindi..bilgisi
            if(response == -1){ // eger sonuc basarili sekilde calistiysa response degeri 1 olacaktir.Eger sorgu bir hata urettiyse -1 olacaktir.
                Helper.showMsg("error");
            }
            return response != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return true;
    }
    //getFetch metodu kullanici kaydi sorgular. user'lari id,name,uname,password ile getirmek icin kullanilir.
    // 'SELECT' sorgulari kullanirak kullanici verilerini veri tabanindan alir ve bir User nesnesine donusturur.
    public static User getFetch(String uname){
        User obj = null; // eger sorgu sonucunda kayot bulunamazsa obj degiskeninin null olacagi anlamina gelir.
        String query = "SELECT * FROM user WHERE uname = ?"; //SQL user tablosundan uname sorgulama
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, uname); // hazirlanan SQL sorgusunda ? yerine uname degerini parametre olarak ekler. bu sorgunun belirli bir uname icin calismasini saglar.
            ResultSet rs = pr.executeQuery(); // pr.executeQuery() ile sorgu calistirilir. sonuc rs degiskenine aktarilir.
            if (rs.next()){ // sorgu sonucunda bir sonraki satirin varligini kontrol eder.
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj; // obj nesnesi doldurulduktan sonra bu nesne dondurulur. eger kayit bulunamazsa null dondurur.
    }

    public static User getFetch(String uname, String password){
        User obj = null;
        String query = "SELECT * FROM user WHERE uname = ? AND password = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, uname);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                 switch (rs.getString("type")) {
                    case "operator" :
                        obj = new Operator();
                        break;
                     default :
                        obj = new User();
                }
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    public static User getFetch(int id){
        User obj = null;
        String query = "SELECT * FROM user WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean delete(int id) { // id kullanilarak kullaniciyi veri tabanindan siler. boolean alir.
        // basarili bir sekilde silinmisse true, degilse false dondurur.
        String query = "DELETE FROM user WHERE id = ?";

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query); //pr nesnesi ile veritabanina baglanti alinir.
            pr.setInt(1 , id);
            ArrayList<Course> courseList = Course.getListByUser(id); //veritabanindan kullaniciya ait kurslari getListByUser metodu ile alir
            for (Course c : courseList) { // dongu ile iterasyon.her kursun kimligi (c.getId()) kullanılarak ilgili kurslar da
                Course.delete(c.getId()); // Course.delete(c.getId()) metodu ile silinir. kullanici silindiginde ona ait kurslarin da silinmesini saglar
            }
            return pr.executeUpdate() != -1; // pr.executeUpdate sql sorgusunu calistiti ve silme islemini gerceklestirir.
            // islem basarili bir sekilde tamamlandiysa bu ifade 0 donmeyecektir. pr.executeUpdate() != -1 ifadesi true donecektir.

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true; //islem basarili sekilde tamamlandiginda true doner
    }

    // kullanicinin kullanici bilgilerini guncellemesi icin kullanilir. uname benzersiz olmali ayni isimde baska kullanici varsa guncellemez.
    public static boolean update(int id, String name, String uname, String password, String type) {
        String query = "UPDATE user SET name=?,uname=?,password=?,type=? WHERE id =?";
        User findUser = User.getFetch(uname); // veritabaninda ayni uname'e sahip baska bir kullanici kontrolu icin getFetvch metodu.
        if(findUser != null && findUser.getId() != id) {
            Helper.showMsg("Bu kullanici adi daha once eklenmistir. Lutfen farkli bir kullanici adi giriniz.");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name );
            pr.setString(2, uname );
            pr.setString(3, password );
            pr.setString(4, type );
            pr.setInt(5, id );

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return true;
    }

    // bu metot belli bir sorgu ile kullanicilari filtrelemek icin kullanilir.id,uname,name...
    public static ArrayList<User> searchUserList(String query) throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        //String query = "SELECT * FROM user"; //bunu silip parantez icine aldik. asagida metot olusturduk query icin
        User obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
    public static String searchQuery(String name, String uname, String type){ // kullanicilari belirli kriterlere gore arayan bir sorgu olusturur.
        String query = "SELECT * FROM user WHERE uname LIKE '%{{uname}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{uname}}", uname);
        query = query.replace("{{name}}", name);
        if(!type.isEmpty()){
            query += " AND type = '{{type}}'";
            query = query.replace("{{type}}", type);
        }
        return query;
    }

    public static ArrayList<User> getListOnlyEducator(){ //yalnizca egitmen olan kullanicilari alir ve userList olarak dondurur.
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user WHERE type = 'educator'";
        User obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
