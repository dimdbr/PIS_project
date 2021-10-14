package services;

import DAO.RoleDAO;
import DAO.UserRoleDAO;
import DTO.Role;
import Factory.DAOFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddUserRoleService implements ServiceInterface {
    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int userId = Integer.parseInt(sid, 10);
        req.setAttribute("userId",userId);
        UserRoleDAO userRoleDAO = null;
        try {
            userRoleDAO = mySQLFactory.getUserRoleDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        RoleDAO roleDAO = null;
        try {
            roleDAO = mySQLFactory.getRoleDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        ArrayList<Role> roles = new ArrayList<>();
        try {
            roles = roleDAO.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<Role> possibleRoles = new ArrayList<>();
        for (Role i : roles) {
            try {
                if (!userRoleDAO.findIfRecordExist(userId,i.getId()))
                {
                    possibleRoles.add(i);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        req.setAttribute("roles",possibleRoles);
        req.getServletContext().getRequestDispatcher("/addUserRole.jsp").forward(req,resp);
        try {
            userRoleDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            roleDAO.closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException {
        String sid = req.getParameter("id");
        int userId = Integer.parseInt(sid, 10);
        String sRoleId = req.getParameter("role");
        int roleId = Integer.parseInt(sRoleId, 10);
        UserRoleDAO userRoleDAO = null;
        try {
            userRoleDAO = mySQLFactory.getUserRoleDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            userRoleDAO.addRole(roleId,userId);
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
}
