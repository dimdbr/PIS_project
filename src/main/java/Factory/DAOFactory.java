package Factory;

import DAO.*;
import DTO.MasterRequest;

import java.io.IOException;
import java.sql.SQLException;

public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static final int POSTGRE = 2;
    public static final int ORACLE = 3;

    public abstract UserDAO getUserDao() throws SQLException, IOException;

    public abstract RoleDAO getRoleDao() throws SQLException, IOException;

    public abstract UserRoleDAO getUserRoleDao() throws SQLException, IOException;

    public abstract StatusDAO getStatusDAO() throws SQLException, IOException;
    public abstract RequestDAO getRequestDao() throws SQLException, IOException;
//    public abstract ReviewDAO getReviewDao();
    public abstract MasterRequestDAO getMasterRequestDao() throws SQLException, IOException;
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
