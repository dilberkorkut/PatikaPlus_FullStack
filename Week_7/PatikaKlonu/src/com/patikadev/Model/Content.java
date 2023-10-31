package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    private int id;
    private String title;
    private String description;
    private String youtubeLink;
    //private String quizQuestions;
    private int course_id;



    public Content () {
    }

    public Content(int id, int course_id, String title, String description, String youtubeLink) {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.description = description;
        this.youtubeLink = youtubeLink;
        //this.quizQuestions = quizQuestions;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }



    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

//    public static ArrayList<Content> getList() {
//        ArrayList<Content> list = new ArrayList<>();
//
//        try {
//            Statement st = DBConnector.getInstance().createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM content");
//            while (rs.next()){
//
//                list.add(new Content(rs.getInt("id"),rs.getString(" Content title"),
//                        rs.getString("description"),rs.getString("youtube link"),
//                        rs.getString("quiz questions")));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());;
//        }
//        return list;
//    }

    public static ArrayList<Content> getContentListByCourseId(int courseId) {
        ArrayList<Content> contentList = new ArrayList<>();
        Content obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM content WHERE course_id = " + courseId);
            while (rs.next()){

                int id = rs.getInt("id");
                String ContentTitle = rs.getString("ContentTitle");
                int course_id1 = rs.getInt("course_id");
                String description = rs.getString("description");
                String youtubeLink = rs.getString("youtubeLink");
                obj = new Content(id, course_id1, ContentTitle, description, youtubeLink);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contentList;
    }

    public static boolean add( int course_id, String title, String description, String youtubeLink) {
        String query = "INSERT INTO content ( course_id, ContentTitle, description, youtubeLink) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1, course_id);
            pr.setString(2, title);
            pr.setString(3, description);
            pr.setString(4, youtubeLink);
            return pr.executeUpdate() != -1 ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

    // bu contente'a cevrilecek. course ismi kullanilarak course objesi alicaz.
    public static Content getFetch(int ID){
        Content content = null; // eger sorgu sonucunda kayot bulunamazsa obj degiskeninin null olacagi anlamina gelir.
        String query = "SELECT * FROM content WHERE id = ?"; //SQL course tablosundan name sorgulama
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, ID); // hazirlanan SQL sorgusunda ? yerine name degerini parametre olarak ekler. bu sorgunun belirli bir uname icin calismasini saglar.
            ResultSet rs = pr.executeQuery(); // pr.executeQuery() ile sorgu calistirilir. sonuc rs degiskenine aktarilir.
            if (rs.next()){ // sorgu sonucunda bir sonraki satirin varligini kontrol eder.
                content = new Content();
                content.setId(rs.getInt("id"));
                content.setCourse_id(rs.getInt("course_id"));
                content.setTitle(rs.getString("title"));
                content.setDescription(rs.getString("description"));
                content.setYoutubeLink(rs.getString("youtubeLink"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return content; // obj nesnesi doldurulduktan sonra bu nesne dondurulur. eger kayit bulunamazsa null dondurur.
    }

    public static boolean delete(int content_id) {
        String query = "DELETE FROM content WHERE id = ?";

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1 , content_id);

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true; //islem basarili sekilde tamamlandiginda true doner
    }



}
