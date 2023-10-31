package com.patikadev.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector { // veri tabani baglantisi olusturmak icin kullanilir.
    private Connection connect = null; // sinifin private bir connect nesnesi olusturulur.

    public Connection connectDB() { // veri tabanina baglanmak icin kullanilir.
        try {
            this.connect = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD);
            //Config sinifindan alinan baglanti bilgilerini kullanarak veri tabani baglantisi olustururuz.
        } catch (SQLException e) {
            System.out.println(e.getMessage());//baglanti olustururken hatalari yakalamak icin try-catch
        }
        return this.connect;
    }

    public static Connection getInstance() { // bu metot DBConnector sinifinin bir ornegini olusturur.
        DBConnector db = new DBConnector();
        return db.connectDB(); // connectDB metodunu cagirarak veri tabani baglantisini dondurur
        //bu metot veritabani baglantisi olustumak baglantiyi kullanmak isteyen diger siniflar tarafindan cagrilabilir.
    }
}
