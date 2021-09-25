package Factory;

import DAO.RoleDAO;
import DAO.UserDAO;
import DAO.UserRoleDAO;

import java.io.IOException;
import java.sql.SQLException;

public  abstract class DAOFactory {
    public static final int MYSQL =1;
    public static final int POSTGRE =2;
    public static final int ORACLE =3;
    public abstract UserDAO getUserDao() throws SQLException, IOException;
    public abstract RoleDAO getRoleDao() throws SQLException, IOException;
    public abstract UserRoleDAO getUserRoleDao() throws SQLException, IOException;
//    public abstract StatusId getStatusDAO();
//    public abstract RequestDAO getRequestDao();
//    public abstract ReviewDAO getReviewDao();
//    public abstract MasterRoleDAO getMasterRoleDao();
    public static DAOFactory getDAOFactory(int factoryDB)
    {
        switch (factoryDB)
        {
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
