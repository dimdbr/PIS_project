package services;

import DAO.UserRoleDAO;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserRoleService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        String suid = req.getParameter("uid");
        int uid = Integer.parseInt(suid,10);
        String srid = req.getParameter("rid");
        int rid = Integer.parseInt(srid,10);
        System.out.println(uid);
        System.out.println(rid);
        UserRoleDAO userRoleDAO  =null;
        try {
            userRoleDAO = mySQLFactory.getUserRoleDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            userRoleDAO.deleteRole(rid,uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("getAllUsersRoles");
        try {
            userRoleDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {

    }
}
