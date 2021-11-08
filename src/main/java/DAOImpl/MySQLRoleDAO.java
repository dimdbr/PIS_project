package DAOImpl;

import DAO.RoleDAO;
import DTO.Role;
import DTO.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;

public class MySQLRoleDAO implements RoleDAO {
    private Connection connection;
    private EntityManager entityManager;

    public static final String getAll = "select *  from roles order by id";
    public static final String createRole = "Insert into roles (name)" + "values(?)";
    public static final String deleteRoleById = "Delete from roles where id = (?)";


    public MySQLRoleDAO(Connection connection)
    {
        this.connection = connection;
    }
    public MySQLRoleDAO(Connection connection,EntityManager entityManager)
    {
        this.connection = connection;
        this.entityManager = entityManager;
    }

    @Override
    public ArrayList<Role> findAll() throws SQLException {

        return (ArrayList<Role>) entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    @Transactional
    public void createRole(String roleName) throws SQLException {
       Role role = new Role(roleName);
        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();


    }

    @Override
    public void deleteRole(int id) throws SQLException {
        Role role = entityManager.find(Role.class,id);
        if(role!=null)
        {
            entityManager.getTransaction().begin();
            entityManager.remove(role);
            entityManager.getTransaction().commit();
        }
    }
}
