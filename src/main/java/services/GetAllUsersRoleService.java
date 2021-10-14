package services;

import DAO.UserRoleDAO;
import DTO.UserRole;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetAllUsersRoleService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        UserRoleDAO userRoleDAO = null;
        try {
            userRoleDAO = mySQLFactory.getUserRoleDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        ArrayList<UserRole> userRoles = null;
        try {
            userRoles = userRoleDAO.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("listUserRoles",userRoles);
        req.getServletContext().getRequestDispatcher("/userRole-list.jsp").forward(req,resp);
        try {
            userRoleDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {

    }
}
