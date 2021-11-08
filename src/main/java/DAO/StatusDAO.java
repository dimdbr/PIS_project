package DAO;

import DTO.Status;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StatusDAO {
    ArrayList<Status> findAll() throws SQLException;

    Status findStatusById(int id) throws SQLException;

    void closeConnection() throws SQLException;
}
