package DAOImpl;

import DAO.StatusDAO;
import DTO.Role;
import DTO.Status;
import DTO.User;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;

public class MySQLStatusDAO implements StatusDAO {
    private Connection connection;
    private EntityManager entityManager;
    public static final String getAll = "select *  from status";
    public static final String findStatusById = "Select * from status where id = (?)";

    public MySQLStatusDAO(Connection connection) {
        this.connection = connection;
    }
    public MySQLStatusDAO(Connection connection,EntityManager entityManager) {
        this.connection = connection;
        this.entityManager = entityManager;
    }

    @Override
    public ArrayList<Status> findAll() throws SQLException {

        return (ArrayList<Status>) entityManager.createQuery("select s from Status s").getResultList();

    }

    @Override
    public Status findStatusById(int id) throws SQLException {
        return entityManager.find(Status.class,id);
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
