package DAO;

import DTO.Role;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoleDAO {
    ArrayList<Role> findAll() throws SQLException;
    void closeConnection() throws SQLException;
    void createRole(String roleName) throws SQLException;
    void deleteRole(int id) throws SQLException;

}
