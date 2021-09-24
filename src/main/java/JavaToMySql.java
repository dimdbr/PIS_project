import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JavaToMySql {

    static final String DB_URL = "jdbc:mysql://localhost:3306/pis";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public static void main(String args[]) throws SQLException, IOException {
        Properties properties = new Properties();
        String fileName = "src/main/resources/config.properties";
        FileInputStream fis = new FileInputStream(fileName);
        properties.load(fis);

        String queryGetAll = "Select * from users";
        String queryInsert = "Insert into users (first_name, last_name, login, password)"+"values (?,?,?,?)";
        connection = DriverManager.getConnection(DB_URL,properties.getProperty("user"),properties.getProperty("password"));

        statement  =connection.createStatement();
        rs = statement.executeQuery(queryGetAll);
        PreparedStatement prepareStatement= connection.prepareStatement(queryInsert);

        while (rs.next())
        {
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("first_name"));
            System.out.println(rs.getString("last_name"));
            System.out.println(rs.getString("login"));
        }

//        prepareStatement.setString(1,"Don");
//        prepareStatement.setString(2,"Baron");
//        prepareStatement.setString(3,"AAAAAAAA");
//        prepareStatement.setString(4,"BBBBBBBB");
//        prepareStatement.execute();
//        rs.close();
//        rs = statement.executeQuery(QueryGetAll);
//        while (rs.next())
//        {
//            System.out.println(rs.getInt("id"));
//            System.out.println(rs.getString("first_name"));
//            System.out.println(rs.getString("last_name"));
//            System.out.println(rs.getString("login"));
//        }
        rs.close();
        statement.close();
        connection.close();
    }

}
