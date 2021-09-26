package DAOImpl;

import DAO.MasterRequestDAO;
import DTO.MasterRequest;
import DTO.Request;
import DTO.UserRole;

import java.sql.*;

public class MySQLMasterRequest implements MasterRequestDAO {
    private Connection connection;
    public static final String getAll = "select *  from master_request";
    public static final String insertMasterRequest = "Insert into master_request (user_id,request_id)" + "values(?,?)";
    public static final String findRequestByMasterId = "Select * from master_request where user_id = (?)";

    public MySQLMasterRequest(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        MasterRequest masterRequest = new MasterRequest();
        while (rs.next()) {
            masterRequest.setId(rs.getInt("id"));
            masterRequest.setUserId(rs.getInt("user_id"));
            masterRequest.setRequestId(rs.getInt("request_id"));

            System.out.println(masterRequest);
        }
        rs.close();
        statement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void createMasterRequestLink(int userId, int requestId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(insertMasterRequest);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, requestId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void findRequestByMastersId(int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findRequestByMasterId);
        preparedStatement.setInt(1, userId);
        ResultSet rs = preparedStatement.executeQuery();
        Request request = new Request();
        if (rs.isBeforeFirst() == false) {
            System.out.println("No requests from that user");
        } else {
            while (rs.next()) {
                System.out.println(rs.getInt("request_id"));
            }
        }
        rs.close();
        preparedStatement.close();
    }
}
