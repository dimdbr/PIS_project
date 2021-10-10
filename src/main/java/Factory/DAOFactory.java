package Factory;

import DAO.*;
import DTO.MasterRequest;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static final int POSTGRE = 2;
    public static final int ORACLE = 3;

    public abstract UserDAO getUserDao() throws SQLException, IOException, NamingException;

    public abstract RoleDAO getRoleDao() throws SQLException, IOException, NamingException;

    public abstract UserRoleDAO getUserRoleDao() throws SQLException, IOException, NamingException;

    public abstract StatusDAO getStatusDAO() throws SQLException, IOException, NamingException;
    public abstract RequestDAO getRequestDao() throws SQLException, IOException, NamingException;
    public abstract RequestReviewDAO getReviewDao() throws SQLException, IOException, NamingException;
    public abstract MasterRequestDAO getMasterRequestDao() throws SQLException, IOException, NamingException;
    public static DAOFactory getDAOFactory(int factoryDB) {
        switch (factoryDB) {
            case MYSQL:
                return new MySQLDAOFactory();
            case POSTGRE:
                return new MySQLDAOFactory();
            case ORACLE:
                return new MySQLDAOFactory();
            default:
                return null;
        }

    }


}
