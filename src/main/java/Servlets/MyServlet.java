package Servlets;

import Factory.DAOFactory;
import services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class MyServlet extends HttpServlet {
    DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    ServiceInterface getAllUsers = new GetAllUsersService();
    ServiceInterface createUser = new CreateNewUserService();
    ServiceInterface deleteUser = new DeleteUserService();
    ServiceInterface updatePassword = new UpdateUserService();
    ServiceInterface getAllUsersRoles = new GetAllUsersRoleService();
    ServiceInterface addUserRole = new AddUserRoleService();
    ServiceInterface deleteUserRole = new DeleteUserRoleService();
    ServiceInterface getAllRequests = new GetAllRequestsService();
    ServiceInterface changeRequestStatus = new ChangeRequestStatusService();
    ServiceInterface createRequest = new CreateRequestService();
    ServiceInterface takeRequest = new SelectRequestService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        Map<String, ServiceInterface> hashMap = new HashMap();
        hashMap.put("/getAllUsers", getAllUsers);
        hashMap.put("/createUser", createUser);
        hashMap.put("/deleteUser",deleteUser);
        hashMap.put("/updatePassword",updatePassword);
        hashMap.put("/getAllUsersRoles",getAllUsersRoles);
        hashMap.put("/addUserRole",addUserRole);
        hashMap.put("/deleteUserRole",deleteUserRole);
        hashMap.put("/getAllRequests",getAllRequests);
        hashMap.put("/changeStatus",changeRequestStatus);
        hashMap.put("/createRequest",createRequest);
        hashMap.put("/takeRequest",takeRequest);
        ServiceInterface service = hashMap.get(action);
        service.get(req, resp, mySQLFactory);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postAction = req.getParameter("postAction");
        Map<String, ServiceInterface> hashMap = new HashMap();
        hashMap.put("createUser", createUser);
        hashMap.put("updateUserPassword",updatePassword);
        hashMap.put("addUserRole",addUserRole);
        hashMap.put("changeRequestStatus",changeRequestStatus);
        hashMap.put("createRequest",createRequest);
        hashMap.put("takeRequest",takeRequest);
        System.out.println(postAction);
        ServiceInterface service = hashMap.get(postAction);
        service.post(req, resp, mySQLFactory);

    }
}
