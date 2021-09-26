package DAO;

import java.sql.SQLException;

public interface UserRoleDAO {
    void findAll() throws SQLException;

    boolean findIfRecordExist(int userId, int roleID) throws SQLException;

    void addRole(int roleId, int userId) throws SQLException;

    void deleteRole(int roleId, int userId) throws SQLException;

    void closeConnection() throws SQLException;
}
