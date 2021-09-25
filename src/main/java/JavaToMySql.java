import DAO.RoleDAO;
import DAO.UserDAO;
import DAO.UserRoleDAO;
import Factory.DAOFactory;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class JavaToMySql {

    public static void main(String args[]) throws SQLException, IOException {

        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        UserDAO userDAO = mySQLFactory.getUserDao();
        System.out.println("All users");
        userDAO.findAll();
//        userDAO.createUser("Dmytro","Dobrynin","DDD","1111");
//        System.out.println("All users after insertion");
//        userDAO.findAll();
//        userDAO.deleteUserByLogin("DDD");
//        System.out.println("All users after delete");
//        userDAO.findAll();
//        System.out.println("Changing users password");
//        userDAO.getUserById(1);
//        userDAO.updateUserPassword(1,"QWERTY");
//        userDAO.getUserById(1);
//        userDAO.closeConnection();

        RoleDAO roleDAO = mySQLFactory.getRoleDao();
//        roleDAO.createRole("Client");
//        roleDAO.createRole("Worker");
//        roleDAO.createRole("Manager");
        roleDAO.findAll();
        roleDAO.closeConnection();

        UserRoleDAO userRoleDAO = mySQLFactory.getUserRoleDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User ID");
        int userId = scanner.nextInt();
        System.out.println("Enter Role ID");
        int roleID = scanner.nextInt();
        userRoleDAO.addRole(roleID,userId);
        userRoleDAO.findAll();


    }

}
