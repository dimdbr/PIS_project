package DAO;

import java.sql.SQLException;

public interface MasterRequestDAO {
    void findAll() throws SQLException;
    void closeConnection() throws SQLException;
    void createMasterRequestLink(int userId, int requestId) throws SQLException;
    void findRequestByMastersId(int userId) throws SQLException;
}
