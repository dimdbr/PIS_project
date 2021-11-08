package services;

import DAO.MasterRequestDAO;
import DAO.RequestDAO;
import DAO.UserRoleDAO;
import DTO.Request;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectRequestService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int userId = Integer.parseInt(sid, 10);
        UserRoleDAO userRoleDAO = null;
        try {
            userRoleDAO = mySQLFactory.getUserRoleDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        RequestDAO requestDAO = null;
        try {
            requestDAO = mySQLFactory.getRequestDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        boolean isMaster = false;
        try {
            isMaster = userRoleDAO.findIfRecordExist(userId, 2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("isMaster", isMaster);
        ArrayList<Request> requests = new ArrayList<>();
        if (isMaster) {
            try {
                requests = requestDAO.findByStatus(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            req.setAttribute("requests", requests);
        }
        System.out.println(requests);
        req.getServletContext().getRequestDispatcher("/takeRequest.jsp").forward(req, resp);
        try {
            userRoleDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            requestDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {
        String sid = req.getParameter("id");
        int userId = Integer.parseInt(sid, 10);
        String sRequestId = req.getParameter("possibleRequest");
        int requestId = Integer.parseInt(sRequestId);
        MasterRequestDAO masterRequestDAO = null;
        RequestDAO requestDAO = null;
        try {
            masterRequestDAO = mySQLFactory.getMasterRequestDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            requestDAO = mySQLFactory.getRequestDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            masterRequestDAO.createMasterRequestLink(userId, requestId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            requestDAO.changeStatus(requestId, 2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            requestDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            masterRequestDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("getAllRequests");

    }
}
