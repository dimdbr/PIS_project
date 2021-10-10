package Servlets;

import DAO.RequestDAO;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/createRequest")
public class CreateRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("userId",Integer.parseInt(id));
        getServletContext().getRequestDispatcher("/create-request.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
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
