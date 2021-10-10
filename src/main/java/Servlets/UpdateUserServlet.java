package Servlets;

import DAO.UserDAO;
import DTO.User;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updatePassword")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
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
        getServletContext().getRequestDispatcher("/update-password.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
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
