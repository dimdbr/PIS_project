package DAOImpl;

import DAO.RoleDAO;
import DTO.Role;
import DTO.User;

import java.sql.*;
import java.util.ArrayList;

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
    public ArrayList<Role> findAll() throws SQLException {
        Statement statement;
        ResultSet rs;
        statement = connection.createStatement();
        rs = statement.executeQuery(getAll);
        ArrayList<Role> roles = new ArrayList<Role>();
        while (rs.next()) {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            roles.add(role);
        }

        rs.close();
        statement.close();
        return roles;
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
