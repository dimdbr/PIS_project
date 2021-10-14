package services;

import DAO.RequestDAO;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateRequestService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("userId",Integer.parseInt(id));
        req.getServletContext().getRequestDispatcher("/create-request.jsp").forward(req,resp);
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {
        RequestDAO requestDAO = null;
        try {
            requestDAO = mySQLFactory.getRequestDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        String sFixPrice = req.getParameter("fixPrice");
        double fixPrice  = Double.parseDouble(sFixPrice);
        String description = req.getParameter("description");
        try {
            requestDAO.createRequest(id,fixPrice,description);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            requestDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("getAllRequests");
    }
}
