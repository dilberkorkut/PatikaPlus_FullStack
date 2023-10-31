package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Quiz {
    private int id;
    private int content_id;
    String question;
    String answer;

    private Content content;

    public Quiz () {
    }

    public Quiz(int id, int content_id, String question, String answer) {
        this.id = id;
        this.content_id = content_id;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static ArrayList<Quiz> getQuizListByContentId(int contentId) {
        ArrayList<Quiz> quizList = new ArrayList<>();
        Quiz quiz = null;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM quiz WHERE content_id = " + contentId);
            while (rs.next()){

                int id = rs.getInt("id");
                contentId = rs.getInt("content_id");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                quiz = new Quiz(id, contentId, question, answer);
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quizList;
    }

    public static boolean add( int content_id, String question, String answer) {
        String query = "INSERT INTO quiz ( content_id, question, answer) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1, content_id);
            pr.setString(2, question);
            pr.setString(3, answer);
            return pr.executeUpdate() != -1 ;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }

}
