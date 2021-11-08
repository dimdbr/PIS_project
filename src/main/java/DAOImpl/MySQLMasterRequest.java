package DAOImpl;

import DAO.MasterRequestDAO;
import DTO.MasterRequest;
import DTO.Request;
import DTO.User;
import DTO.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLMasterRequest implements MasterRequestDAO {
    private final Connection connection;
    private EntityManager entityManager;
    public static final String getAll = "select *  from master_request";
    public static final String insertMasterRequest = "Insert into master_request (user_id,request_id)" + "values(?,?)";
    public static final String findRequestByMasterId = "Select * from master_request where user_id = (?)";

    public MySQLMasterRequest(Connection connection) {
        this.connection = connection;
    }

    public MySQLMasterRequest(Connection connection, EntityManager entityManager) {
        this.connection = connection;
        this.entityManager = entityManager;
    }

    @Override
    public ArrayList<MasterRequest> findAll() throws SQLException {
//        Statement statement;
//        ResultSet rs;
//        statement = connection.createStatement();
//        rs = statement.executeQuery(getAll);
//        MasterRequest masterRequest = new MasterRequest();
//        while (rs.next()) {
//            masterRequest.setId(rs.getInt("id"));
//            masterRequest.setUserId(rs.getInt("user_id"));
//            masterRequest.setRequestId(rs.getInt("request_id"));
//
//            System.out.println(masterRequest);
//        }
//        rs.close();
//        statement.close();
        return (ArrayList<MasterRequest>) entityManager.createQuery("select mr from MasterRequest mr").getResultList();

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void createMasterRequestLink(int userId, int requestId) throws SQLException {
        MasterRequest masterRequest = new MasterRequest();
        masterRequest.setUser(entityManager.find(User.class,userId));
        masterRequest.setRequest(entityManager.find(Request.class,requestId));
        System.out.println(masterRequest);
        entityManager.getTransaction().begin();
        entityManager.persist(masterRequest);
        entityManager.getTransaction().commit();
    }

    @Override
    public ArrayList<Request> findRequestByMastersId(int userId) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(findRequestByMasterId);
//        preparedStatement.setInt(1, userId);
//        ResultSet rs = preparedStatement.executeQuery();
//        Request request = new Request();
//        if (rs.isBeforeFirst() == false) {
//            System.out.println("No requests from that user");
//        } else {
//            while (rs.next()) {
//                System.out.println(rs.getInt("request_id"));
//            }
//        }
//        rs.close();
//        preparedStatement.close();
//        Query query = entityManager.createNamedQuery("MasterRequest.findByUserId");
//        query.setParameter("user_id", userId);

        User master = entityManager.find(User.class, userId);
        List<MasterRequest> masterRequests = master.getTakenRequests();
        ArrayList<Request> requests = new ArrayList<>();
        for (MasterRequest mr : masterRequests
        ) {
            requests.add(mr.getRequest());
        }
//        ArrayList<MasterRequest> masterRequests = entityManager.find(MasterRequest.class, null);
        return requests;
    }
}
