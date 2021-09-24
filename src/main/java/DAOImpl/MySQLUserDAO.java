package DAOImpl;

import DAO.UserDAO;
import DTO.User;

import java.sql.*;

public class MySQLUserDAO implements UserDAO {

    private Connection connection;
    public static final String getAll = "select *  from users";
    public static final String createUser = "Insert into users (first_name,last_name,login,password)" + "values(?,?,?,?)";
    public static final String deleteUserByLogin = "Delete from users where login = (?)";
    public static final String deleteUserById = "Delete from users where id = (?)";
    public static final String updateUserPassword = "Update users set password = (?) where id=(?)";
    public static final String findUSerById = "Select * from users where id = (?)";

    public MySQLUserDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        User user = new User();
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            System.out.println(user);
        }
        rs.close();
        statement.close();

    }

    @Override
    public void createUser(String firstName, String lastName, String login, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(createUser);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, login);
        preparedStatement.setString(4, password);
        preparedStatement.execute();
        preparedStatement.close();

    }

    @Override
    public void deleteUserById(int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserById);
        preparedStatement.setInt(1, userId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteUserByLogin(String userLogin) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(deleteUserByLogin);
        preparedStatement.setString(1, userLogin);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void getUserById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(findUSerById);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            System.out.println(user);
        }
        rs.close();
        preparedStatement.close();

    }

    @Override
    public void updateUserPassword(int userId, String newPassword) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(updateUserPassword);
        preparedStatement.setString(1, newPassword);
        preparedStatement.setInt(2, userId);
        preparedStatement.execute();
        preparedStatement.close();

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
