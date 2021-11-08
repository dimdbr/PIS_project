package DAOImpl;

import DAO.RequestDAO;
import DTO.Request;
import DTO.Status;
import DTO.User;
import DTO.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;

public class MySQLRequestDAO implements RequestDAO {
    private Connection connection;
    private EntityManager entityManager;
    public static final String getAll = "select *  from repair_request";
    public static final String findRequestById = "Select * from repair_request where id = (?)";
    public static final String createRequest = "Insert into repair_request (user_id,fix_price,description)" + "values(?,?,?)";
    public static final String findRequestByUserId = "Select * from repair_request where user_id = (?)";
    public static final String findRequestByStatusId = "Select * from repair_request where status_id = (?)";
    public static final String updateRequestStatus = "Update repair_request set status_id = (?) where id = (?)";

    public MySQLRequestDAO(Connection connection) {
        this.connection = connection;
    }

    public MySQLRequestDAO(Connection connection, EntityManager entityManager) {
        this.connection = connection;
        this.entityManager = entityManager;
    }

    @Override
    public ArrayList<Request> findAll() throws SQLException {
//        Statement statement;
//        ResultSet rs;
//        statement = connection.createStatement();
//
//        rs = statement.executeQuery(getAll);
//        ArrayList<Request> requests = new ArrayList<>();
//
//        while (rs.next()) {
//            Request request = new Request();
//            request.setId(rs.getInt("id"));
//            request.setFixPrice(rs.getDouble("fix_price"));
//            request.setUserId(rs.getInt("user_id"));
//            request.setStatusId(rs.getInt("status_id"));
//            request.setDescription(rs.getString("description"));
//            requests.add(request);
//        }
//        rs.close();
//        statement.close();
//        return requests;
        return (ArrayList<Request>) entityManager.createQuery("select r from Request r order by r.status.id ").getResultList();

    }

    @Override
    public Request findById(int id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(findRequestById);
//        preparedStatement.setInt(1, id);
//        ResultSet rs = preparedStatement.executeQuery();
//        Request request = new Request();
//        while (rs.next()) {
//            request.setId(rs.getInt("id"));
//            request.setFixPrice(rs.getDouble("fix_price"));
//            request.setUserId(rs.getInt("user_id"));
//            request.setStatusId(rs.getInt("status_id"));
//            request.setDescription(rs.getString("description"));
//            System.out.println(request);
//        }
//        rs.close();
//        preparedStatement.close();
        return entityManager.find(Request.class, id);

    }

    @Override
    public void createRequest(int userId, double fixPrice, String description) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(createRequest);
//        preparedStatement.setInt(1, userId);
//        preparedStatement.setDouble(2, fixPrice);
//        preparedStatement.setString(3, description);
//        preparedStatement.execute();
//        preparedStatement.close();
        Request request = new Request(fixPrice, userId, description);
        entityManager.getTransaction().begin();
        entityManager.persist(request);
        entityManager.getTransaction().commit();
    }


    @Override
    public void findByUserId(int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findRequestByUserId);
        preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        Request request = new Request();
        if (rs.isBeforeFirst() == false) {
            System.out.println("No requests from that user");
        } else {
            while (rs.next()) {
                request.setId(rs.getInt("id"));
                request.setFixPrice(rs.getDouble("fix_price"));
                request.setUserId(rs.getInt("user_id"));
//                request.setStatusId(rs.getInt("status_id"));
                request.setDescription(rs.getString("description"));
                System.out.println(request);
            }
        }
        rs.close();
        preparedStatement.close();
    }

    @Override
    public ArrayList<Request> findByStatus(int statusId) throws SQLException {

//        PreparedStatement preparedStatement = connection.prepareStatement(findRequestByStatusId);
//        preparedStatement.setInt(1, statusId);
//        ResultSet rs = preparedStatement.executeQuery();
//        ArrayList<Request> requests = new ArrayList<>();
//        if (rs.isBeforeFirst() == false) {
//            System.out.println("No requests with that status");
//        } else {
//            while (rs.next()) {
//                Request request = new Request();
//                request.setId(rs.getInt("id"));
//                request.setFixPrice(rs.getDouble("fix_price"));
//                request.setUserId(rs.getInt("user_id"));
//                request.setStatusId(rs.getInt("status_id"));
//                request.setDescription(rs.getString("description"));
//                requests.add(request);
//            }
//        }
//        rs.close();
//        preparedStatement.close();
//        return requests;

        Query query = entityManager.createNamedQuery("Request.findByStatus");
        query.setParameter("status_id",statusId);
        return (ArrayList<Request>) query.getResultList();
    }

    @Override
    public void changeStatus(int requestId, int statusId) throws SQLException {
        entityManager.getTransaction().begin();
        Request request = findById(requestId);
        Status status = entityManager.find(Status.class,statusId);
        request.setStatus(status);
        entityManager.merge(request);
        entityManager.getTransaction().commit();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
