package DAOImpl;

import DAO.RequestReviewDAO;
import DTO.MasterRequest;
import DTO.Request;
import DTO.RequestReview;
import DTO.User;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLRequestReview implements RequestReviewDAO {
    private Connection connection;
    private EntityManager entityManager;
    public static final String getAll = "select *  from request_review";
    public static final String findReviewById = "Select * from request_review where id = (?)";
    public static final String findByUserId = "Select * from request_review where user_id = (?)";
    public static final String createReview = "Insert into request_review (user_id,request_id,text_review)" + "values(?,?,?)";

    public MySQLRequestReview(Connection connection) {
        this.connection = connection;
    }

    public MySQLRequestReview(Connection connection, EntityManager entityManager) {
        this.connection = connection;
        this.entityManager = entityManager;
    }

    @Override
    public ArrayList<RequestReview> findAll() throws SQLException {
//        Statement statement;
//        ResultSet rs;
//        statement = connection.createStatement();
//        rs = statement.executeQuery(getAll);
//        RequestReview requestReview = new RequestReview();
//        while (rs.next()) {
//            requestReview.setId(rs.getInt("id"));
//            requestReview.setUserId(rs.getInt("user_id"));
//            requestReview.setRequestId(rs.getInt("request_id"));
//            requestReview.setTextReview(rs.getString("text_review"));
//
//            System.out.println(requestReview);
//        }
//        rs.close();
//        statement.close();
        return (ArrayList<RequestReview>) entityManager.createQuery("select rr from RequestReview rr").getResultList();

    }

    @Override
    public RequestReview findById(int id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(findReviewById);
//        preparedStatement.setInt(1, id);
//        ResultSet rs = preparedStatement.executeQuery();
//        RequestReview requestReview = new RequestReview();
//        while (rs.next()) {
//            requestReview.setId(rs.getInt("id"));
//            requestReview.setUserId(rs.getInt("user_id"));
//            requestReview.setRequestId(rs.getInt("request_id"));
//            requestReview.setTextReview(rs.getString("text_review"));
//
//            System.out.println(requestReview);
//        }
//        rs.close();
//        preparedStatement.close();
        return entityManager.find(RequestReview.class, id);
    }

    @Override
    public ArrayList<RequestReview> findByUserId(int userId) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(findByUserId);
//        preparedStatement.setInt(1, userId);
//        ResultSet rs = preparedStatement.executeQuery();
//        RequestReview requestReview = new RequestReview();
//        while (rs.next()) {
//            requestReview.setId(rs.getInt("id"));
//            requestReview.setUserId(rs.getInt("user_id"));
//            requestReview.setRequestId(rs.getInt("request_id"));
//            requestReview.setTextReview(rs.getString("text_review"));
//
//            System.out.println(requestReview);
//        }
//        rs.close();
//        preparedStatement.close();
        User user = entityManager.find(User.class, userId);
        List<RequestReview> requestReviews = user.getReviewedRequests();
        return (ArrayList<RequestReview>) requestReviews;

    }

    @Override
    public void createReview(int userId, int requestId, String review) throws SQLException {
        RequestReview requestReview = new RequestReview();
        requestReview.setRequest(entityManager.find(Request.class,requestId));
        requestReview.setUser(entityManager.find(User.class,userId));
        requestReview.setTextReview(review);
        entityManager.getTransaction().begin();
        entityManager.persist(requestReview);
        entityManager.getTransaction().commit();

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
