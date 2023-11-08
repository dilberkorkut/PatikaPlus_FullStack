package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OperatorGUI extends JFrame { // biz bunu JFrameden uretiyoruz.
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JLabel lbl_welcome;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list; // tablo icin bir modele ihtiyac var. tablolar modellerle calisiyorlar. model icin de 2 tane parametre var. column sutun basliklari ve row satirlari tutacak.
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_password;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JButton btn_user_delete;
    private JTextField fld_user_id;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_username;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_sh;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;

    private JTable tbl_allContent;

    private JTable tbl_op_cont_list;
    private JButton btn_op_cont_delete;
    private JTextField fld_op_cont_id;
    private DefaultTableModel mdl_user_list; // userList icin panel scrool tablo ve model
    private Object[] row_user_list;  //rowlarimi listelerimi bir array icinde tutucaz. butun siniflar objeden turetildigi icin ne veri atacaksak obje turunde aticaz

    private DefaultTableModel mdl_patika_list;

    private DefaultTableModel mdl_op_cont_list;
    private Object[] row_op_cont_list;


    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;

    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;




    private final Operator operator;
    // gui icinde her zaman operaor islem yapacagi icin operatore ait tum islemleri buranin icerisine yazmayi bekleyecegi
    // bu ara yuz cagrildiginda icerine zorulu olarak operator atmak gerekiyo

    public OperatorGUI(Operator operator) {
        this.operator = operator;

        Helper.setLayout();
        add(wrapper);
        setSize(1000, 500);
       // int x = (Toolkit.getDefaultToolkit().getScreenSize().width = getSize().width) / 2;
       // int y = (Toolkit.getDefaultToolkit().getScreenSize().height = getSize().height) / 2; // her dafasinda bu kadar yazmamak icin bir helper package olusturuyoruz

       // int x = Helper.screenCenterPoint("x", getSize());
        // int y = Helper.screenCenterPoint("y", getSize());

        // yada soyle bir ifade saglayabiliriz

        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // x butonu ozelligini veriyoruz
        //setTitle("Patika.Dev"); // projenin basligi. her seferinde ayni sabit yazilacaksa..Config sinifi olusturduk. oradan sabit title cagiriyoruz
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hosgeldin : " + operator.getName());

        // UserList -start
        mdl_user_list = new DefaultTableModel() { ; // constructorda nesnemi olusturdum


            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"ID" , "Ad Soyad" , "Kullanici Adi" , "Sifre" , "Uyelik Tipi"}; // column isimlerini atiyoruz.
        mdl_user_list.setColumnIdentifiers(col_user_list); // modelimin tablo basliklarini yapmis oluyoruz.
        row_user_list = new Object[col_user_list.length];
        loadUserModel();
        /*
        Object[] firstRow = {"1", "Mustafa Cetindag", "mustafa", "123", "operator"};
        mdl_user_list.addRow(firstRow); // veri eklenir. */

        // bunu dinamik hale getirmek icin veri tabanindan cektiklerimizi for dongusuyle aktarabiliriz.



        tbl_user_list.setModel(mdl_user_list); //tablolarin modelleri mevcut. modelleri table icerine gomuyoruz
        tbl_user_list.getTableHeader().setReorderingAllowed(false); // bu basliklarin yeniden siralanabilir ozelligini kaldiriyoruz.

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try{
            String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 0).toString();
            fld_user_id.setText(select_user_id);
            } catch (Exception exception) {
              //  System.out.println(exception.getMessage());
            }

        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 0).toString());
                String user_name =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 1).toString();
                String user_uname =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 2).toString();
                String user_password =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 3).toString();
                String user_type =tbl_user_list.getValueAt(tbl_user_list.getSelectedRow() , 4).toString();

                if(User.update(user_id, user_name, user_uname, user_password, user_type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                }
                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }

        });
        // USerList finish


        //PatikaList start

        patikaMenu = new JPopupMenu(); // varsayilan bir nesne uretiyoruz. tablo icerisinde menu icinde sagi tiklayinca guncelle ve sil butonu ciukacak.
        JMenuItem updateMenu = new JMenuItem("Guncelle"); // buton olustur patika menuye ekle(patikaMenu.add)
        JMenuItem deleteMenu = new JMenuItem("Sil"); // sil buton olustur patika menuye ekle(patikaMenu.add)
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
                if (Patika.delete(select_id)) {
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID", "Patika Adi"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();

        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu); // yukaridaki patikaMenu yu tabloya atiyouruz.
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { // hangi rowu sectigini bulabilicez. guncelle mi sil mi
                Point point = e.getPoint(); // Point icerisinde x ve y degerleri tutan hazir bir sinif/
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        //PatikaList finish

        //CourseList start

        mdl_course_list = new DefaultTableModel();
        Object[] col_courseList = {"ID", "Ders Adi", "Programlama Dili", "Patika", "Egitmen"};
        mdl_course_list.setColumnIdentifiers(col_courseList);
        row_course_list = new Object[col_courseList.length];
        loadCourseModel();
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadPatikaCombo();
        loadEducatorCombo();

        // CourseList finish

        // Operator - Content - Start

        mdl_op_cont_list = new DefaultTableModel();
        Object[] col_op_cont_list = {"ID", "Ders Adi"};
        mdl_op_cont_list.setColumnIdentifiers(col_op_cont_list);
        row_op_cont_list = new Object[col_op_cont_list.length];
        loadOpContentModel();

        tbl_op_cont_list.setModel(mdl_op_cont_list);
        tbl_op_cont_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_op_cont_list.getTableHeader().setReorderingAllowed(false);

        tbl_op_cont_list.getSelectionModel().addListSelectionListener(e -> { //
            try {
                String select_op_cont_id = tbl_op_cont_list.getValueAt(tbl_op_cont_list.getSelectedRow(), 0).toString();
                fld_op_cont_id.setText(select_op_cont_id);
            } catch (Exception exception) {
                //  System.out.println(exception.getMessage());
            }
        });


        // Operator - Content - Finish

        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_password)) {
                Helper.showMsg("fill");
            } else{
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String password = fld_user_password.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name, uname, password, type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    loadEducatorCombo();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_password.setText(null);

                } else {
                    Helper.showMsg("error");
                }
            }

        });
        btn_user_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_user_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")){
                    int user_id = Integer.parseInt(fld_user_id.getText());
                    if(User.delete(user_id)) {
                        Helper.showMsg("done");
                        loadUserModel();
                        loadEducatorCombo();
                        loadCourseModel();
                        fld_user_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });


        btn_user_sh.addActionListener(e -> {
            String name = fld_sh_user_name.getText();
            String uname = fld_sh_user_username.getText();
            String type = cmb_sh_user_type.getSelectedItem().toString();

            String query = User.searchQuery(name, uname, type);

            try {
                ArrayList<User> searchingUser = User.searchUserList(query);
                loadUserModel(User.searchUserList(query));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        btn_logout.addActionListener(e -> {
            dispose(); // varoldugu cagrildigi frame'i kapatir. kapatma islemini yapar
            LoginGUI login = new LoginGUI();

        });
        btn_patika_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_patika_name)) {
                Helper.showMsg("fill"); // deger yoksa true show msj fill cagiriyoruz
            } else {
                if(Patika.add(fld_patika_name.getText())) {
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    fld_patika_name.setText(null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        btn_course_add.addActionListener(e -> {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)) {
                Helper.showMsg("fill");
            } else {
                if (Course.add(userItem.getKey(),patikaItem.getKey(), fld_course_name.getText(), fld_course_lang.getText())) {
                    Helper.showMsg("done");
                    loadCourseModel();
                    fld_course_lang.setText(null);

                    fld_patika_name.setText(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });

        //=======operator-content-delete==========
        btn_op_cont_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_op_cont_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")){
                    int content_id = Integer.parseInt(fld_op_cont_id.getText());
                    if(Content.delete(content_id)) {
                        Helper.showMsg("done");
                        loadOpContentModel();
                        fld_user_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
    }

    public void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);

        int i = 0;

        for(Course obj : Course.getList()){
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLang();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i++] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }
    }

    public void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel(); // her islem yaptigimda o modeli temizleyip tablonun icerisine geri aktaricam.
        clearModel.setRowCount(0); // icindeki tum verileri temizliyoruz 0 diyerek/

        int i = 0;
        for(Patika obj : Patika.getList()) {
            i=0;
            row_patika_list[i++] =obj.getId();
            row_patika_list[i++] =obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for(User obj : User.getList()) {
            int i = 0;

            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list); // model icerisine verileri atiyoruz.
        }
    }

    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for(User obj : list) {
            int i = 0;

            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPassword();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list); // model icerisine verileri atiyoruz.
        }
    }

    public void loadPatikaCombo (){
        cmb_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()) {
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));

        }
    }
    public void loadEducatorCombo(){
        cmb_course_user.removeAllItems();
        for (User obj : User.getListOnlyEducator()) {
            cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
// Operator-Content-List-Model
    public static ArrayList<Content> getOpContentList(){
        ArrayList<Content> contentList= new ArrayList<>();
        Content obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM content");
            while(rs.next()){
                obj=new Content(rs.getInt("id"),rs.getInt("course_id"),rs.getString("ContentTitle") ,rs.getString("description"),rs.getString("youtubeLink"));
                contentList.add(obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentList;
    }
    public void loadOpContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_op_cont_list.getModel();
        clearModel.setRowCount(0);
        for (Content obj : getOpContentList()) {
            row_op_cont_list[0] = obj.getId();
            row_op_cont_list[1] = obj.getTitle();

            mdl_op_cont_list.addRow(row_op_cont_list);
        }
    }


    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("Mustafa Cetindag");
        op.setPassword("1234");
        op.setType("operator");
        op.setUname("mustafa");


        OperatorGUI opGUI = new OperatorGUI(op);
    }

}
