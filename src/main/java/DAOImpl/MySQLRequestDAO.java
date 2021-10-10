package DAOImpl;

import DAO.RequestDAO;
import DTO.Request;
import DTO.User;
import DTO.UserRole;

import java.sql.*;
import java.util.ArrayList;

public class MySQLRequestDAO implements RequestDAO {
    private Connection connection;
    public static final String getAll = "select *  from repair_request";
    public static final String findRequestById = "Select * from repair_request where id = (?)";
    public static final String createRequest = "Insert into repair_request (user_id,fix_price,description)" + "values(?,?,?)";
    public static final String findRequestByUserId = "Select * from repair_request where user_id = (?)";
    public static final String findRequestByStatusId = "Select * from repair_request where status_id = (?)";
    public static final String updateRequestStatus = "Update repair_request set status_id = (?) where id = (?)";

    public MySQLRequestDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<Request> findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();

        rs = statement.executeQuery(getAll);
        ArrayList<Request> requests = new ArrayList<>();

        while (rs.next()) {
            Request request = new Request();
            request.setId(rs.getInt("id"));
            request.setFixPrice(rs.getDouble("fix_price"));
            request.setUserId(rs.getInt("user_id"));
            request.setStatusId(rs.getInt("status_id"));
            request.setDescription(rs.getString("description"));
            requests.add(request);
        }
        rs.close();
        statement.close();
        return requests;
    }

    @Override
    public void findById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findRequestById);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        Request request = new Request();
        while (rs.next()) {
            request.setId(rs.getInt("id"));
            request.setFixPrice(rs.getDouble("fix_price"));
            request.setUserId(rs.getInt("user_id"));
            request.setStatusId(rs.getInt("status_id"));
            request.setDescription(rs.getString("description"));
            System.out.println(request);
        }
        rs.close();
        preparedStatement.close();


    }

    @Override
    public void createRequest(int userId, double fixPrice, String description) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(createRequest);
        preparedStatement.setInt(1, userId);
        preparedStatement.setDouble(2, fixPrice);
        preparedStatement.setString(3, description);
        preparedStatement.execute();
        preparedStatement.close();
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
                request.setStatusId(rs.getInt("status_id"));
                request.setDescription(rs.getString("description"));
                System.out.println(request);
            }
        }
        rs.close();
        preparedStatement.close();
    }

    @Override
    public void findByStatus(int statusId) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(findRequestByStatusId);
        preparedStatement.setInt(1, statusId);
        ResultSet rs = preparedStatement.executeQuery();
        Request request = new Request();
        if (rs.isBeforeFirst() == false) {
            System.out.println("No requests with that status");
        } else {
            while (rs.next()) {
                request.setId(rs.getInt("id"));
                request.setFixPrice(rs.getDouble("fix_price"));
                request.setUserId(rs.getInt("user_id"));
                request.setStatusId(rs.getInt("status_id"));
                request.setDescription(rs.getString("description"));
                System.out.println(request);
            }
        }
        rs.close();
        preparedStatement.close();
    }

    @Override
    public void changeStatus(int requestId, int statusId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(updateRequestStatus);
        preparedStatement.setInt(1, statusId);
        preparedStatement.setInt(2, requestId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
