package DAO;

import DTO.RequestReview;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RequestReviewDAO {
    ArrayList<RequestReview> findAll() throws SQLException;
    RequestReview findById(int id) throws SQLException;
    ArrayList<RequestReview> findByUserId(int userId) throws SQLException;
    void closeConnection() throws SQLException;
    void createReview(int userId, int requestId, String review) throws SQLException;
}
