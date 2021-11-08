package Factory;

import DAO.*;
import DAOImpl.*;
import db.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class MySQLDAOFactory extends DAOFactory{
    public static final String DB_URL = "jdbc:mysql://localhost:3306/pis";
    private Connection connection;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

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
        return new MySQLUserDAO(createConnection(),entityManager);
    }
    @Override
    public MySQLRoleDAO getRoleDao() throws SQLException, IOException, NamingException {
        return new MySQLRoleDAO(createConnection(),entityManager);
    }

    @Override
    public UserRoleDAO getUserRoleDao() throws SQLException, IOException, NamingException {
        return new MySQLUserRoleDAO(createConnection(),entityManager);
    }

    @Override
    public StatusDAO getStatusDAO() throws SQLException, IOException, NamingException {
        return new MySQLStatusDAO(createConnection(),entityManager);
    }

    @Override
    public RequestDAO getRequestDao() throws SQLException, IOException, NamingException {
        return new MySQLRequestDAO(createConnection(),entityManager);
    }

    @Override
    public MasterRequestDAO getMasterRequestDao() throws SQLException, IOException, NamingException {
        return new MySQLMasterRequest(createConnection(),entityManager);
    }

    @Override
    public RequestReviewDAO getReviewDao() throws SQLException, IOException, NamingException {
        return new MySQLRequestReview(createConnection(),entityManager);
    }
}
