package services;

import DAO.UserDAO;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateNewUserService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/create-user.jsp").forward(req,resp);

    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {
        UserDAO userDAO = null;
        try {
            userDAO = mySQLFactory.getUserDao();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            userDAO.createUser(firstName,lastName,login,password);
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
