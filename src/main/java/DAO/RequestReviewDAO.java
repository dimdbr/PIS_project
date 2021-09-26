package DAO;

import java.sql.SQLException;

public interface RequestReviewDAO {
    void findAll() throws SQLException;
    void findById(int id) throws SQLException;
    void findByUserId(int userId) throws SQLException;
    void closeConnection() throws SQLException;
    void createReview(int userId, int requestId, String review) throws SQLException;
}
