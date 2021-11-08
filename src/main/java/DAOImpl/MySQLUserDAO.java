package DAOImpl;

import DAO.UserDAO;
import DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;

//@Service
//@Repository
//@Transactional
public class MySQLUserDAO implements UserDAO {
    private final Connection connection;
//    @PersistenceContext(unitName = "unit")
//    @Autowired
    private EntityManager entityManager;

    public static final String getAll = "select *  from users";
    public static final String createUser = "Insert into users (first_name,last_name,login,password)" + "values(?,?,?,?)";
    public static final String deleteUserByLogin = "Delete from users where login = (?)";
    public static final String deleteUserById = "Delete from users where id = (?)";
    public static final String updateUserPassword = "Update users set password = (?) where id=(?)";
    public static final String findUSerById = "Select * from users where id = (?)";

    public MySQLUserDAO(Connection connection) {
        this.connection = connection;
    }

    public MySQLUserDAO(Connection connection, EntityManager entityManager) {
        this.connection = connection;
        this.entityManager = entityManager;
    }


    @Override
    public ArrayList<User> findAll() throws SQLException {

        System.out.println(entityManager);
        return (ArrayList<User>) entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void createUser(String firstName, String lastName, String login, String password) throws SQLException {

        User user = new User(firstName, lastName, login, password);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUserById(int userId) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserById);
//        preparedStatement.setInt(1, userId);
//        preparedStatement.execute();
//        preparedStatement.close();
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void deleteUserByLogin(String userLogin) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserByLogin);
//        preparedStatement.setString(1, userLogin);
//        preparedStatement.execute();
//        preparedStatement.close();
        User user = entityManager.find(User.class, userLogin);
        if (user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }

    }

    @Override
    public User getUserById(int id) throws SQLException {

        return entityManager.find(User.class, id);

    }

    @Override
    public void updateUserPassword(int userId, String newPassword) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(updateUserPassword);
//        preparedStatement.setString(1, newPassword);
//        preparedStatement.setInt(2, userId);
//        preparedStatement.execute();
//        preparedStatement.close();
        User user = getUserById(userId);
        user.setPassword(newPassword);
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
