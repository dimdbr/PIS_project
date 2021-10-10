package DAOImpl;

import DAO.StatusDAO;
import DTO.Status;
import DTO.User;

import java.sql.*;
import java.util.ArrayList;

public class MySQLStatusDAO implements StatusDAO {
    private Connection connection;
    public static final String getAll = "select *  from status";
    public static final String findStatusById = "Select * from status where id = (?)";

    public MySQLStatusDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<Status> findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        ArrayList<Status> statuses = new ArrayList<>();
        while (rs.next()) {
            Status status = new Status();
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("status"));
            statuses.add(status);
            System.out.println(status);
        }
        rs.close();
        statement.close();
        return statuses;
    }

    @Override
    public void findStatusById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findStatusById);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        Status status = new Status();
        while (rs.next()) {
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("status"));

            System.out.println(status);
        }
        rs.close();
        preparedStatement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
