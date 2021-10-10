package Factory;

import DAO.*;
import DAOImpl.*;
import db.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDAOFactory extends DAOFactory{
    public static final String DB_URL = "jdbc:mysql://localhost:3306/pis";
    private Connection connection;

    public Connection createConnection() throws IOException, SQLException, NamingException {
//        Properties properties = new Properties();
//        String fileName = "src/main/resources/config.properties";
//        FileInputStream fis = new FileInputStream(fileName);
//        properties.load(fis);
//        Context ctx=new InitialContext();
//        Context envContext = (Context)ctx.lookup("java:comp/env");
         return this.connection = ConnectionPool.getInstance().getConnection();


    }



    @Override
    public MySQLUserDAO getUserDao() throws SQLException, IOException, NamingException {
        return new MySQLUserDAO(createConnection());
    }
    @Override
    public MySQLRoleDAO getRoleDao() throws SQLException, IOException, NamingException {
        return new MySQLRoleDAO(createConnection());
    }

    @Override
    public UserRoleDAO getUserRoleDao() throws SQLException, IOException, NamingException {
        return new MySQLUserRoleDAO(createConnection());
    }

    @Override
    public StatusDAO getStatusDAO() throws SQLException, IOException, NamingException {
        return new MySQLStatusDAO(createConnection());
    }

    @Override
    public RequestDAO getRequestDao() throws SQLException, IOException, NamingException {
        return new MySQLRequestDAO(createConnection());
    }

    @Override
    public MasterRequestDAO getMasterRequestDao() throws SQLException, IOException, NamingException {
        return new MySQLMasterRequest(createConnection());
    }

    @Override
    public RequestReviewDAO getReviewDao() throws SQLException, IOException, NamingException {
        return new MySQLRequestReview(connection);
    }
}
