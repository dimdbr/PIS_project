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
import java.util.ArrayList;

public class GetAllUsersService implements ServiceInterface {


    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        UserDAO userDAO = null;
        try {
            userDAO = mySQLFactory.getUserDao();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        ArrayList<User> users = null;
        try {
            users = userDAO.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("listUser",users);
        req.getServletContext().getRequestDispatcher("/user-list.jsp").forward(req,resp);
        try {
            userDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) {

    }
}
