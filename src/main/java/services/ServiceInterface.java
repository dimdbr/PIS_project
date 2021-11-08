package services;

import Factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServiceInterface {
     void get(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws ServletException, IOException;
     void post(HttpServletRequest req, HttpServletResponse resp, DAOFactory mySQLFactory) throws IOException;

}
