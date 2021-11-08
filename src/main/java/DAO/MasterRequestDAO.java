package DAO;

import DTO.MasterRequest;
import DTO.Request;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MasterRequestDAO {
    ArrayList<MasterRequest> findAll() throws SQLException;
    void closeConnection() throws SQLException;
    void createMasterRequestLink(int userId, int requestId) throws SQLException;
    ArrayList<Request> findRequestByMastersId(int userId) throws SQLException;
}
