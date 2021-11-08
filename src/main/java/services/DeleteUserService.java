package services;

import DAO.UserDAO;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid,10);
        UserDAO userDAO = null;
        try {
            userDAO = mySQLFactory.getUserDao();
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        try {
            assert userDAO != null;
            userDAO.deleteUserById(id);
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

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {

    }
}
