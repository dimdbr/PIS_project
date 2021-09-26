package DAO;

import java.sql.SQLException;

public interface RoleDAO {
    void findAll() throws SQLException;
    void closeConnection() throws SQLException;
    void createRole(String roleName) throws SQLException;
    void deleteRole(int id) throws SQLException;

}
