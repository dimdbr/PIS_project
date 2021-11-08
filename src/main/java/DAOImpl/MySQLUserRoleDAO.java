package DAOImpl;

import DAO.UserRoleDAO;
import DTO.Role;
import DTO.User;
import DTO.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MySQLUserRoleDAO implements UserRoleDAO {
    private Connection connection;
    private EntityManager entityManager;
    public static final String getAll = "select *  from user_roles";
    public static final String findRecord = "select * from user_roles where user_id = (?) and role_id = (?)";
    public static final String insertUserRole = "Insert into user_roles (user_id,role_id)" + "values(?,?)";
    public static final String deleteUserRole = "Delete from user_roles where user_id = (?) and role_id = (?)";

    public MySQLUserRoleDAO(Connection connection) {
        this.connection = connection;
    }

    public MySQLUserRoleDAO(Connection connection, EntityManager entityManager) {
        this.connection = connection;
        this.entityManager = entityManager;
    }

    @Override
    public ArrayList<UserRole> findAll() throws SQLException {

        return (ArrayList<UserRole>) entityManager.createQuery("select ur from UserRole ur").getResultList();

    }

    @Override
    public boolean findIfRecordExist(int userId, int roleId) throws SQLException {

        Query query = entityManager.createNamedQuery("User.findUserRole");
        query.setParameter("user_id",userId);
        query.setParameter("role_id",roleId);
        List result = query.getResultList();
        if(result.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void addRole(int roleId, int userId) throws SQLException {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class,userId);
        Role role = entityManager.getReference(Role.class,roleId);
        user.getRoleList().add(role);
        entityManager.merge(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteRole(int roleId, int userId) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserRole);
//        preparedStatement.setInt(1, userId);
//        preparedStatement.setInt(2, roleId);
//        preparedStatement.execute();
//        preparedStatement.close();

        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class,userId);
        Role role = entityManager.getReference(Role.class,roleId);
        user.getRoleList().remove(role);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
