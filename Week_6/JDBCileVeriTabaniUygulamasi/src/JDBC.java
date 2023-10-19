import java.sql.*;

public class JDBC {

    public static final String DB_URL = "jdbc:mysql://localhost/patika";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "mysql";

    public static void main(String[] args) {


        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("position"));
                System.out.println(resultSet.getString("salary"));

            }
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
