package services;

import DAO.RequestDAO;
import DAO.StatusDAO;
import DTO.Status;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChangeRequestStatusService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {

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
        req.getServletContext().getRequestDispatcher("/changeStatus.jsp").forward(req, resp);
        try {
            statusDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);

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
            requestDAO.changeStatus(id, statusId);
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
