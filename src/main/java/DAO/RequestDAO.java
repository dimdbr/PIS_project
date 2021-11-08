package DAO;

import DTO.Request;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RequestDAO {
    ArrayList<Request> findAll() throws SQLException;

    Request findById(int id) throws SQLException;

    void createRequest(int userId,double fixPrice,String description ) throws SQLException;

    void findByUserId(int userId) throws SQLException;

    ArrayList<Request> findByStatus(int statusId) throws SQLException;

    void changeStatus(int requestId, int statusId) throws SQLException;

    void closeConnection() throws SQLException;
}
