package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EducatorGUI extends JFrame{
    private JPanel wrapper;
    private JLabel lbl_welcome_edu;
    private JPanel pnl_top_edu;
    private JButton btn_logout_edu;
    private JTable tbl_edu_course_list;
    private JTable tbl_edu_content_list;
    private JPanel pnl_edu_course_list;
    private JPanel pnl_edu_content_list;
    private JPanel pnl_edu_quiz_list;
    private JScrollPane scrl_edu_course_list;
    private JScrollPane scrl_edu_content_list;
    private JScrollPane scrl_edu_quiz_list;
    private JTable tbl_edu_quiz_list;
    private JTextField fld_edu_course_id;
    private JButton btn_edu_course_select;
    private JTabbedPane tab_educator;
    private JTextField fld_edu_content_title;
    private JTextField fld_edu_content_description;
    private JTextField fld_edu_content_link;
    private JButton btn_edu_content_add;
    private JComboBox cmb_edu_content_course_list;
    private JTextField fld_edu_quiz_content_id;
    private JButton btn_edu_quiz_select;
    private JTextField fld_edu_quiz_question;
    private JTextField fld_edu_quiz_answer;
    private JButton btn_edu_quiz_add;
    private JComboBox cmb_edu_quiz_content;
    private DefaultTableModel mdl_edu_course_list;
    private DefaultTableModel mdl_edu_content_list;
    private DefaultTableModel mdl_edu_quiz_list;

    private Object[] row_edu_course_list;
    private Object[] row_edu_content_list;
    private Object[] row_edu_quiz_list;
    private Educator educator;
    private User user;
    private Course course;
    private Content content;




    public EducatorGUI(Educator educator) {
        this.educator = educator;

        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(true);
        setVisible(true);

       // cmb_edu_content_course_list.setVisible(false); // combobox gozukmesin
        fld_edu_quiz_content_id.setVisible(false);

        lbl_welcome_edu.setText("Hosgeldiniz " + educator.getName()); //setText dedigim icin yaziyi degistirebildim.

        //Course List - Start
        mdl_edu_course_list = new DefaultTableModel();

        Object[] col_edu_course_list = {"id", "Patika Ismi", "Ders Adi", "Programlama Dili"};
        mdl_edu_course_list.setColumnIdentifiers(col_edu_course_list); //modelimize sutunlari set ediyoruz.
        row_edu_course_list = new Object[col_edu_course_list.length];
        loadEduCourseList();
        tbl_edu_course_list.setModel(mdl_edu_course_list);
        tbl_edu_course_list.getTableHeader().setReorderingAllowed(false);

        tbl_edu_course_list.getSelectionModel().addListSelectionListener(e -> { //
            try {
                String select_course_id = tbl_edu_course_list.getValueAt(tbl_edu_course_list.getSelectedRow(), 0).toString();
                fld_edu_course_id.setText(select_course_id);
            } catch (Exception exception) {
                //  System.out.println(exception.getMessage());
            }

        });

        mdl_edu_content_list = new DefaultTableModel();
        Object[] col_edu_content_list = {"ID", "Course ID", "Baslik", "Aciklama", "Youtube Link"};
        mdl_edu_content_list.setColumnIdentifiers(col_edu_content_list);
        row_edu_content_list = new Object[col_edu_content_list.length];
        tbl_edu_content_list.setModel(mdl_edu_content_list);

        tbl_edu_content_list.getSelectionModel().addListSelectionListener(e -> { //
            try {
                String select_content_id = tbl_edu_content_list.getValueAt(tbl_edu_content_list.getSelectedRow(), 0).toString();
                fld_edu_quiz_content_id.setText(select_content_id);
            } catch (Exception exception) {
                //  System.out.println(exception.getMessage());
            }
        });

//course -select course_id = secince otomatik yazilan id
        btn_edu_course_select.addActionListener(e ->
                loadEduContentList());


        btn_edu_content_add.addActionListener(e -> {

            //Item eduCourse = (Item) cmb_edu_content_course_list.getSelectedItem();
            if (Helper.isFieldEmpty(fld_edu_content_title) || Helper.isFieldEmpty(fld_edu_content_description) || Helper.isFieldEmpty(fld_edu_content_link) || Helper.isComboBoxEmpty(cmb_edu_content_course_list)) {
                Helper.showMsg("fill");
            }

            Course course = Course.getFetch(cmb_edu_content_course_list.getSelectedItem().toString());

            int course_id = course.getId();
            String title = fld_edu_content_title.getText();
            String description = fld_edu_content_description.getText();
            String yt_link = fld_edu_content_link.getText();

            if (Content.add(course_id, title, description, yt_link)) {
                Helper.showMsg("success");
                loadEduContentList();

                fld_edu_content_title.setText(null);
                fld_edu_content_description.setText(null);
                fld_edu_content_link.setText(null);
            }
        });


        mdl_edu_quiz_list = new DefaultTableModel();
        Object[] col_edu_quiz_list = {"ID", "Content ID", "Question", "Answer"};
        mdl_edu_quiz_list.setColumnIdentifiers(col_edu_quiz_list);
        row_edu_quiz_list = new Object[col_edu_quiz_list.length];
        tbl_edu_quiz_list.setModel(mdl_edu_quiz_list);

        btn_edu_quiz_select.addActionListener(e ->
                loadEduQuizList());

        btn_edu_quiz_add.addActionListener(e -> {

          //  Item contentItem = (Item) cmb_edu_quiz_content.getSelectedItem();

            if (Helper.isComboBoxEmpty(cmb_edu_quiz_content) || Helper.isFieldEmpty(fld_edu_quiz_question) || Helper.isFieldEmpty(fld_edu_quiz_answer)) {
                Helper.showMsg("fill");
            }

           // Content content = Content.getFetch(fld_edu_quiz_content_id.getText());

            int content_id = Integer.parseInt(fld_edu_quiz_content_id.getText());
            String question = fld_edu_quiz_question.getText();
            String answer = "";

            if (Quiz.add(content_id, question, answer)) {
                Helper.showMsg("success");
                loadEduQuizList();

                fld_edu_quiz_content_id.setText(null);
                fld_edu_quiz_question.setText(null);
               // fld_edu_quiz_answer.setText(null);
            }
        });
    }

    public void loadEduCourseList() {
        DefaultTableModel clearEduModel = (DefaultTableModel) tbl_edu_course_list.getModel();
        clearEduModel.setRowCount(0);

        for(Course course : Course.getListByUser(educator.getId())) {

            row_edu_course_list[0] = course.getId();
            row_edu_course_list[1] = Patika.getFetch(course.getPatika_id()).getName();
            row_edu_course_list[2] = course.getName();
            row_edu_course_list[3] = course.getLang();
            mdl_edu_course_list.addRow(row_edu_course_list);

        }
    }

    public void loadEduContentList() {
        DefaultTableModel clearEduModel = (DefaultTableModel) tbl_edu_content_list.getModel();
        clearEduModel.setRowCount(0);
        int course_id = Integer.parseInt(fld_edu_course_id.getText());
        for (Content content : Content.getContentListByCourseId(course_id)) {
            row_edu_content_list[0] = content.getId();
            row_edu_content_list[1] = content.getCourse_id();
            row_edu_content_list[2] = content.getTitle();
            row_edu_content_list[3] = content.getDescription();
            row_edu_content_list[4] = content.getYoutubeLink();
            mdl_edu_content_list.addRow(row_edu_content_list);
        }

       cmb_edu_content_course_list.removeAllItems();


        int educatorId = educator.getId();
        ArrayList<Course> listEdu = Course.getListByUser(educatorId);
        for(Course course : listEdu) {
            cmb_edu_content_course_list.addItem(course.getName());
        }
    }

    public void loadEduQuizList() {
        DefaultTableModel clearEduModel = (DefaultTableModel) tbl_edu_quiz_list.getModel();
        clearEduModel.setRowCount(0);
        int content_id = Integer.parseInt(fld_edu_quiz_content_id.getText());
        for (Quiz quiz : Quiz.getQuizListByContentId(content_id)) {
            row_edu_quiz_list[0] = quiz.getId();
            row_edu_quiz_list[1] = quiz.getContent_id();
            row_edu_quiz_list[2] = quiz.getQuestion();
            row_edu_quiz_list[3] = quiz.getAnswer();
            mdl_edu_quiz_list.addRow(row_edu_quiz_list);
        }

   //     cmb_edu_quiz_content.removeAllItems();

//        int contentId = content.getId();
//        ArrayList<Content> quizList = Content.getContentListByCourseId(contentId);
//        for(Content content : quizList) {
//            cmb_edu_quiz_content.addItem(content.getTitle());
//        }
    }
}
