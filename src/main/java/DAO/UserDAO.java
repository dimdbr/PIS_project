package DAO;

import java.sql.SQLException;

public interface UserDAO {
    void findAll() throws SQLException;
    void createUser(String firstName, String lastName, String login, String password) throws SQLException;
    void getUserById(int id) throws SQLException;
    void deleteUserById(int userId) throws SQLException;
    void deleteUserByLogin(String userLogin) throws SQLException;
    void updateUserPassword(int userId, String newPassword) throws SQLException;
    void closeConnection() throws SQLException;
}
