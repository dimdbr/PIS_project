import DAO.UserDAO;
import Factory.DAOFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JavaToMySql {

    public static void main(String args[]) throws SQLException, IOException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        UserDAO userDAO = mySQLFactory.getUserDao();
        System.out.println("All users");
        userDAO.findAll();
        userDAO.createUser("Dmytro","Dobrynin","DDD","1111");
        System.out.println("All users after insertion");
        userDAO.findAll();
        userDAO.deleteUserByLogin("DDD");
        System.out.println("All users after delete");
        userDAO.findAll();
        System.out.println("Changing users password");
        userDAO.getUserById(1);
        userDAO.updateUserPassword(1,"QWERTY");
        userDAO.getUserById(1);
        userDAO.closeConnection();



//        prepareStatement.setString(1,"Don");
//        prepareStatement.setString(2,"Baron");
//        prepareStatement.setString(3,"AAAAAAAA");
//        prepareStatement.setString(4,"BBBBBBBB");
//        prepareStatement.execute();
//        rs.close();
//        rs = statement.executeQuery(QueryGetAll);
//        while (rs.next())
//        {
//            System.out.println(rs.getInt("id"));
//            System.out.println(rs.getString("first_name"));
//            System.out.println(rs.getString("last_name"));
//            System.out.println(rs.getString("login"));
//        }
    }

}
