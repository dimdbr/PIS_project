package DAO;

import java.sql.SQLException;

public interface StatusDAO {
    void findAll() throws SQLException;

    void findStatusById(int id) throws SQLException;

    void closeConnection() throws SQLException;
}
