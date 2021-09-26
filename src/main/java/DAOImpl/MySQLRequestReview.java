package DAOImpl;

import DAO.RequestReviewDAO;
import DTO.MasterRequest;
import DTO.Request;
import DTO.RequestReview;
import DTO.User;

import java.sql.*;

public class MySQLRequestReview implements RequestReviewDAO {
    private Connection connection;
    public static final String getAll = "select *  from request_review";
    public static final String findReviewById = "Select * from request_review where id = (?)";
    public static final String findByUserId = "Select * from request_review where user_id = (?)";
    public static final String createReview = "Insert into request_review (user_id,request_id,text_review)" + "values(?,?,?)";

    public MySQLRequestReview(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        RequestReview requestReview = new RequestReview();
        while (rs.next()) {
            requestReview.setId(rs.getInt("id"));
            requestReview.setUserId(rs.getInt("user_id"));
            requestReview.setRequestId(rs.getInt("request_id"));
            requestReview.setTextReview(rs.getString("text_review"));

            System.out.println(requestReview);
        }
        rs.close();
        statement.close();
    }

    @Override
    public void findById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findReviewById);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        RequestReview requestReview = new RequestReview();
        while (rs.next()) {
            requestReview.setId(rs.getInt("id"));
            requestReview.setUserId(rs.getInt("user_id"));
            requestReview.setRequestId(rs.getInt("request_id"));
            requestReview.setTextReview(rs.getString("text_review"));

            System.out.println(requestReview);
        }
        rs.close();
        preparedStatement.close();
    }

    @Override
    public void findByUserId(int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findByUserId);
        preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        RequestReview requestReview = new RequestReview();
        while (rs.next()) {
            requestReview.setId(rs.getInt("id"));
            requestReview.setUserId(rs.getInt("user_id"));
            requestReview.setRequestId(rs.getInt("request_id"));
            requestReview.setTextReview(rs.getString("text_review"));

            System.out.println(requestReview);
        }
        rs.close();
        preparedStatement.close();
    }

    @Override
    public void createReview(int userId, int requestId, String review) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(createReview);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, requestId);
        preparedStatement.setString(3, review);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
