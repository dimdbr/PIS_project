package Servlets;

import DAO.MasterRequestDAO;
import DTO.Request;
import DAO.RequestDAO;
import DAO.UserRoleDAO;
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

@WebServlet("/takeRequest")
public class SelectRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int userId = Integer.parseInt(sid, 10);
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
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
        if(isMaster)
        {
            try {
                requests = requestDAO.findByStatus(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            req.setAttribute("possibleRequests",requests);
        }
        getServletContext().getRequestDispatcher("/takeRequest.jsp").forward(req,resp);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int userId = Integer.parseInt(sid, 10);
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
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
            masterRequestDAO.createMasterRequestLink(userId,requestId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            requestDAO.changeStatus(requestId,2);
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
