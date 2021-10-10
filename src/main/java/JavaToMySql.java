import DAO.*;
import DTO.MasterRequest;
import DTO.RequestReview;
import Factory.DAOFactory;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class JavaToMySql {

    public static void main(String args[]) throws SQLException, IOException, NamingException {

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
//        System.out.println("Enter User ID");
//        int userId = scanner.nextInt();
//        System.out.println("Enter Role ID");
//        int roleID = scanner.nextInt();
//        userRoleDAO.addRole(roleID,userId);
//        userRoleDAO.findAll();

        StatusDAO statusDAO = mySQLFactory.getStatusDAO();
        statusDAO.findAll();
//        System.out.println("Enter Status ID");
//        int statusId = scanner.nextInt();
//        statusDAO.findStatusById(statusId);
//        statusDAO.closeConnection();
        RequestDAO requestDAO = mySQLFactory.getRequestDao();
//        requestDAO.createRequest(3,1200.15,"Some description");
        requestDAO.findAll();
//        requestDAO.findByUserId(4);
//        requestDAO.findByUserId(3);
//        requestDAO.findByStatus(1);
//        requestDAO.changeStatus(1,2);
//        requestDAO.findByStatus(2);
        requestDAO.closeConnection();

        MasterRequestDAO masterRequestDAO = mySQLFactory.getMasterRequestDao();
//        masterRequestDAO.createMasterRequestLink(4,1);
        masterRequestDAO.findAll();
        masterRequestDAO.closeConnection();

        RequestReviewDAO requestReviewDAO = mySQLFactory.getReviewDao();
        requestReviewDAO.createReview(3,1,"Well done");
        requestReviewDAO.findAll();
        requestReviewDAO.closeConnection();
    }

}
