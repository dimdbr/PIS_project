package Servlets;

import DAO.RequestDAO;
import DAO.StatusDAO;
import DAO.UserRoleDAO;
import DTO.Request;
import DTO.Status;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/changeStatus")
public class ChangeRequestStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        StatusDAO statusDAO = null;
        try {
            statusDAO = mySQLFactory.getStatusDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        ArrayList<Status> statuses = new ArrayList<>();
        try {
            statuses = statusDAO.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("statuses", statuses);
        getServletContext().getRequestDispatcher("/changeStatus.jsp").forward(req,resp);
        try {
            statusDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        RequestDAO requestDAO = null;
        try {
            requestDAO = mySQLFactory.getRequestDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        String sStatusId = req.getParameter("status");
        int statusId = Integer.parseInt(sStatusId);
        try {
            requestDAO.changeStatus(id,statusId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("getAllRequests");
        try {
            requestDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
