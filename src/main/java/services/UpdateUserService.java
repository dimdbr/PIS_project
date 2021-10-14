package services;

import DAO.UserDAO;
import DTO.User;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        UserDAO userDAO = null;
        try {
            userDAO = mySQLFactory.getUserDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid,10);
        String password = new String();
        try {
            User user =  userDAO.getUserById(id);
            password = user.getPassword();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("oldPassword",password);
        req.getServletContext().getRequestDispatcher("/update-password.jsp").forward(req,resp);
        try {
            userDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {
        UserDAO userDAO = null;
        try {
            userDAO = mySQLFactory.getUserDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid,10);
        String newPassword = req.getParameter("password");
        try {
            userDAO.updateUserPassword(id,newPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            userDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("getAllUsers");
    }
}
