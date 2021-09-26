package DAOImpl;

import DAO.RoleDAO;
import DTO.Role;
import DTO.User;

import java.sql.*;

public class MySQLRoleDAO implements RoleDAO {
    private Connection connection;
    public static final String getAll = "select *  from roles order by id";
    public static final String createRole = "Insert into roles (name)" + "values(?)";
    public static final String deleteRoleById = "Delete from roles where id = (?)";


    public MySQLRoleDAO(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        Role role = new Role();
        while (rs.next()) {
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            System.out.println(role);
        }
        rs.close();
        statement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void createRole(String roleName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(createRole);
        preparedStatement.setString(1, roleName);
        preparedStatement.execute();
        preparedStatement.close();


    }

    @Override
    public void deleteRole(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(deleteRoleById);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
