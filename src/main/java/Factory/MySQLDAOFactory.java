package Factory;

import DAO.UserDAO;
import DAOImpl.MySQLUserDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDAOFactory extends DAOFactory{
    public static final String DB_URL = "jdbc:mysql://localhost:3306/pis";
    private Connection connection;
    public Connection createConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        String fileName = "src/main/resources/config.properties";
        FileInputStream fis = new FileInputStream(fileName);
        properties.load(fis);
        this.connection = DriverManager.getConnection(DB_URL,properties.getProperty("user"),properties.getProperty("password"));
        return this.connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public UserDAO getUserDao() throws SQLException, IOException {
        return new MySQLUserDAO(createConnection());
    }

}