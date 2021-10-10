package DAOImpl;

import DAO.UserRoleDAO;
import DTO.User;
import DTO.UserRole;

import java.sql.*;
import java.util.ArrayList;

public class MySQLUserRoleDAO implements UserRoleDAO {
    private Connection connection;
    public static final String getAll = "select *  from user_roles";
    public static final String findRecord = "select * from user_roles where user_id = (?) and role_id = (?)";
    public static final String insertUserRole = "Insert into user_roles (user_id,role_id)" + "values(?,?)";
    public static final String deleteUserRole = "Delete from user_roles where user_id = (?) and role_id = (?)";

    public MySQLUserRoleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<UserRole> findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        ArrayList<UserRole> userRoles = new ArrayList<UserRole>();
        while (rs.next()) {
            UserRole userRole = new UserRole();
            userRole.setId(rs.getInt("id"));
            userRole.setRoleId(rs.getInt("role_id"));
            userRole.setUserId(rs.getInt("user_id"));
            userRoles.add(userRole);
        }

        rs.close();
        statement.close();
        connection.close();
        return userRoles;
    }

    @Override
    public boolean findIfRecordExist(int userId, int roleId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findRecord);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, roleId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultSet.close();
            preparedStatement.close();
            return true;

        } else {
            resultSet.close();
            preparedStatement.close();
            return false;
        }

    }

    @Override
    public void addRole(int roleId, int userId) throws SQLException {
        // how to check that role exist
        if (findIfRecordExist(userId, roleId)) {
            System.out.println("row already exist");
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserRole);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, roleId);
            preparedStatement.execute();
            preparedStatement.close();
        }
    }

    @Override
    public void deleteRole(int roleId, int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserRole);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, roleId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
