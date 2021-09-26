package DAO;

import java.sql.SQLException;

public interface RequestDAO {
    void findAll() throws SQLException;

    void findById(int id) throws SQLException;

    void createRequest(int userId,double fixPrice,String description ) throws SQLException;

    void findByUserId(int userId) throws SQLException;

    void findByStatus(int statusId) throws SQLException;

    void changeStatus(int requestId, int statusId) throws SQLException;

    void closeConnection() throws SQLException;
}
