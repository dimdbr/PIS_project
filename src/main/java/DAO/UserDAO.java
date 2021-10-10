package DAO;

import DTO.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    ArrayList<User> findAll() throws SQLException;
    void createUser(String firstName, String lastName, String login, String password) throws SQLException;
    User getUserById(int id) throws SQLException;
    void deleteUserById(int userId) throws SQLException;
    void deleteUserByLogin(String userLogin) throws SQLException;
    void updateUserPassword(int userId, String newPassword) throws SQLException;
    void closeConnection() throws SQLException;
}
