package DAOImpl;

import DAO.StatusDAO;
import DTO.Status;
import DTO.User;

import java.sql.*;

public class MySQLStatusDAO implements StatusDAO {
    private Connection connection;
    public static final String getAll = "select *  from status";
    public static final String findStatusById = "Select * from status where id = (?)";

    public MySQLStatusDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        Status status = new Status();
        while (rs.next()) {
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("status"));

            System.out.println(status);
        }
        rs.close();
        statement.close();
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
