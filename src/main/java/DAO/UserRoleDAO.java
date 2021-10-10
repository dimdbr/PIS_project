package DAO;

import DTO.UserRole;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserRoleDAO {
    ArrayList<UserRole> findAll() throws SQLException;

    boolean findIfRecordExist(int userId, int roleID) throws SQLException;

    void addRole(int roleId, int userId) throws SQLException;

    void deleteRole(int roleId, int userId) throws SQLException;

    void closeConnection() throws SQLException;
}
