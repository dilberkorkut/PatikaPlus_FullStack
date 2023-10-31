package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_student;
    private JTable tbl_stu_patika_list;
    private JScrollPane scrl_stu_patika_list;
    private JPanel pnl_stu_patika_list;
    private JTextField fld_stu_patika_id;
    private JButton btn_stu_patika_select;
    private JLabel lbl_stu_welcome;
    private JPanel pnl_stu_course_list;
    private JScrollPane scrl_stu_course_list;
    private JTable tbl_stu_course_list;
    private JTextField fld_stu_course_id;
    private JScrollPane scrl_stu_reg_course_list;
    private JTable tbl_stu_reg_course_list;
    private JButton btn_stu_select_content_list;
    private JScrollPane scrl_stu_content_list;
    private JTable tbl_stu_content_list;
    private JTextField fld_stu_content_name;
    private JTextField fld_stu_content_description;
    private JTextField fld_stu_content_quiz;
    private JTextField fld_stu_content_answer;
    private JButton btn_stu_select_content;
    private JButton btn_stu_content_submit;
    private JTextField fld_stu_reg_course_id;
    private JButton btn_stu_course_select_register;
    private JTextField fld_stu_content_link;


    private DefaultTableModel mdl_stu_patika_list;
    private DefaultTableModel mdl_stu_course_list;
    private DefaultTableModel mdl_stu_reg_course_list;
    private DefaultTableModel mdl_stu_content_list;

    private Object[] row_stu_patika_list;
    private Object[] row_stu_course_list;
    private Object[] row_stu_reg_course_list;
    private Object[] row_stu_content_list;

    private Student student;




    public StudentGUI(Student student) {
        this.student = student;

        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()) , Helper.screenCenterPoint("y" , getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_stu_welcome.setText("Hosgeldiniz " + student.getName());

        //=======================

        mdl_stu_patika_list = new DefaultTableModel();
        Object[] col_stu_patika_list = {"id", "Patika Ismi"};
        mdl_stu_patika_list.setColumnIdentifiers(col_stu_patika_list);
        row_stu_patika_list = new Object[col_stu_patika_list.length];
        loadStuPatikaModel();

        tbl_stu_patika_list.setModel(mdl_stu_patika_list);
        tbl_stu_patika_list.getTableHeader().setReorderingAllowed(false);

        tbl_stu_patika_list.getSelectionModel().addListSelectionListener(e -> { //
            try {
                String select_patika_id = tbl_stu_patika_list.getValueAt(tbl_stu_patika_list.getSelectedRow(), 0).toString();
                fld_stu_patika_id.setText(select_patika_id);
            } catch (Exception exception) {
                //  System.out.println(exception.getMessage());
            }
        });


        //=======================


        mdl_stu_course_list = new DefaultTableModel();
        Object[] col_stu_course_list = {"id", "Course Adi", "Programlama Dili"};
        mdl_stu_course_list.setColumnIdentifiers(col_stu_course_list);
        row_stu_course_list = new Object[col_stu_course_list.length];

        tbl_stu_course_list.setModel(mdl_stu_course_list);
        tbl_stu_course_list.getTableHeader().setReorderingAllowed(false);

        tbl_stu_course_list.getSelectionModel().addListSelectionListener(e -> { //
            try {
                String select_course_id = tbl_stu_course_list.getValueAt(tbl_stu_course_list.getSelectedRow(), 0).toString();
                fld_stu_course_id.setText(select_course_id);
            } catch (Exception exception) {
                //  System.out.println(exception.getMessage());
            }
        });


        //=======================

        mdl_stu_reg_course_list = new DefaultTableModel();
        Object[] col_stu_reg_course_list = {"id", "Course Adi", "Patika Adi"};
        mdl_stu_reg_course_list.setColumnIdentifiers(col_stu_reg_course_list);
        row_stu_reg_course_list = new Object[col_stu_reg_course_list.length];
        loadStuRegCourseModel();

        tbl_stu_reg_course_list.setModel(mdl_stu_reg_course_list);
        tbl_stu_reg_course_list.getTableHeader().setReorderingAllowed(false);

        tbl_stu_reg_course_list.getSelectionModel().addListSelectionListener(e -> { //
            try {
                String select_reg_course_id = tbl_stu_reg_course_list.getValueAt(tbl_stu_reg_course_list.getSelectedRow(), 0).toString();
                fld_stu_reg_course_id.setText(select_reg_course_id);
            } catch (Exception exception) {
                //  System.out.println(exception.getMessage());
            }
        });

        //==========================

        mdl_stu_content_list = new DefaultTableModel();
        Object[] col_stu_content_list = {"id", "Content Name"};
        mdl_stu_content_list.setColumnIdentifiers(col_stu_content_list);
        row_stu_content_list = new Object[col_stu_content_list.length];

        tbl_stu_content_list.setModel(mdl_stu_content_list);
        tbl_stu_content_list.getTableHeader().setReorderingAllowed(false);

//        tbl_stu_content_list.getSelectionModel().addListSelectionListener(e -> { //
//            try {
//                String select_content_id = tbl_stu_content_list.getValueAt(tbl_stu_content_list.getSelectedRow(), 0).toString();
//                fld_stu_reg_course_id.setText(select_content_id);
//            } catch (Exception exception) {
//                //  System.out.println(exception.getMessage());
//            }
//        });



        btn_stu_patika_select.addActionListener(e -> {
            loadStuCourseModel();
        });


        btn_stu_course_select_register.addActionListener(e -> {
            String courseName = tbl_stu_course_list.getValueAt(tbl_stu_course_list.getSelectedRow(), 1).toString();
            String courseLang = tbl_stu_course_list.getValueAt(tbl_stu_course_list.getSelectedRow(), 2).toString();

            //String select_course_id = tbl_stu_course_list.getValueAt(tbl_stu_course_list.getSelectedRow(), 0).toString();

            if (Helper.isFieldEmpty(fld_stu_patika_id) || Helper.isFieldEmpty(fld_stu_course_id)) {
                Helper.showMsg("fill");
            } else {
                if (addRegCourse(student.getId(), Integer.parseInt(fld_stu_patika_id.getText()), courseName, courseLang)) {
                    Helper.showMsg("done");
                    loadStuRegCourseModel();

                } else {
                    Helper.showMsg("error");
                }
            }

        });

        btn_stu_select_content_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               loadStuContentModel();
            }
        });

        btn_stu_select_content.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStuContentDetails();

            }
        });
        btn_stu_content_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Helper.showMsg("Turizm Acentesi Projesine Gitmelisin! Hala burada misin?");
            }
        });
    }
    public static ArrayList<Patika> getPaths() {
        ArrayList<Patika> pathList = new ArrayList<>();

        Patika obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patika");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                obj = new Patika(id, name);
                pathList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pathList;
    }

    private void loadStuPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_patika_list.getModel();
        clearModel.setRowCount(0);
        for (Patika obj : getPaths()) {
            row_stu_patika_list[0] = obj.getId();
            row_stu_patika_list[1] = obj.getName();
            mdl_stu_patika_list.addRow(row_stu_patika_list);
        }
    }


    public static ArrayList<Course> getCoursebyPath(int p_id) {
        ArrayList<Course> courseList = new ArrayList<>();

        Course obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE patika_id = " + p_id);
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                int patika_id = rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");

                obj = new Course(id, user_id,patika_id, name, lang);
                courseList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }


    private void loadStuCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_course_list.getModel();
        clearModel.setRowCount(0);
       int patikaID = Integer.parseInt(fld_stu_patika_id.getText());
        for (Course obj : getCoursebyPath(patikaID)) {
            row_stu_course_list[0] = obj.getId();
            row_stu_course_list[1] = obj.getName();
            row_stu_course_list[2] = obj.getLang();
            mdl_stu_course_list.addRow(row_stu_course_list);
        }
    }

    public static ArrayList<Course> getRegCoursebyUser(int userID) {
        ArrayList<Course> regCourseList = new ArrayList<>();

        Course obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registered_courses WHERE user_id = " + userID);
            while (rs.next()) {
                int ID = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int patikaId = rs.getInt("patika_id");
                String courseName = rs.getString("course_name");
                String lang = rs.getString("lang");

                obj = new Course(ID, userId, patikaId, courseName, lang);
                regCourseList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regCourseList;
    }


    private void loadStuRegCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_reg_course_list.getModel();
        clearModel.setRowCount(0);

        for (Course obj : getRegCoursebyUser(student.getId())) {
            row_stu_reg_course_list[0] = obj.getId();
            row_stu_reg_course_list[1] = obj.getName();
            row_stu_reg_course_list[2] = obj.getLang();
            mdl_stu_reg_course_list.addRow(row_stu_reg_course_list);
        }
    }

    public static ArrayList<Content> getStuContentDetails(int contentID) {
        ArrayList<Content> contentDetailsList = new ArrayList<>();

        Content obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM content WHERE id = " + contentID);
            while (rs.next()) {
                int id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String contentTitle = rs.getString("ContentTitle");
                String description = rs.getString("description");
                String youtubeLink = rs.getString("youtubeLink");

                obj = new Content(id, course_id, contentTitle, description, youtubeLink);
                contentDetailsList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentDetailsList;
    }

    public static ArrayList<Quiz> getStuQuizByContentId(int contentID) {
        ArrayList<Quiz> quizArrayList = new ArrayList<>();

        Quiz obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM quiz WHERE content_id = " + contentID);
            while (rs.next()) {
                int id = rs.getInt("id");
                int content_id = rs.getInt("content_id");
                String question = rs.getString("question");
                String answer = rs.getString("answer");


                obj = new Quiz(id, content_id , question, answer);
                quizArrayList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizArrayList;
    }


    private void loadStuContentDetails() {
//        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_content_list.getModel();
//        clearModel.setRowCount(0);
        int content_id = Integer.parseInt(tbl_stu_content_list.getValueAt(tbl_stu_content_list.getSelectedRow(), 0 ).toString());
        for (Content obj : getStuContentDetails(content_id)) {
            fld_stu_content_name.setText(obj.getTitle());
            fld_stu_content_description.setText(obj.getDescription());
            fld_stu_content_link.setText(obj.getYoutubeLink());
        }

        for (Quiz obj : getStuQuizByContentId(content_id)) {
            fld_stu_content_quiz.setText(obj.getQuestion());

        }
    }

    public static ArrayList<Content> getStuContentByRegCourse(int courseID) {
        ArrayList<Content> contentList = new ArrayList<>();

        Content obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM content WHERE course_id = " + courseID);
            while (rs.next()) {
                int id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String contentTitle = rs.getString("ContentTitle");
                String description = rs.getString("description");
                String youtubeLink = rs.getString("youtubeLink");

                obj = new Content(id, course_id, contentTitle, description, youtubeLink);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentList;
    }


    private void loadStuContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_content_list.getModel();
        clearModel.setRowCount(0);
        int courseId = Integer.parseInt(tbl_stu_reg_course_list.getValueAt(tbl_stu_reg_course_list.getSelectedRow(), 0 ).toString());
        for (Content obj : getStuContentByRegCourse(courseId)) {
            row_stu_content_list[0] = obj.getId();
            row_stu_content_list[1] = obj.getTitle();
            mdl_stu_content_list.addRow(row_stu_content_list);
        }
    }


    public static boolean addRegCourse(int user_id, int patika_id, String name, String lang) {
        String query = "INSERT INTO registered_courses (user_id, patika_id, course_name, lang) VALUES (?,?,?,?)";
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
}
