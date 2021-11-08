package DTO;

import javax.persistence.*;

@Entity
@Table(name = "master_request")
//@NamedQuery(name = "MasterRequest.findByUserId",query = "select mr from MasterRequest mr where mr.userId=:user_id")
public class MasterRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "user_id")
//    private int userId;
//    @Column(name = "request_id")
//    private int requestId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public MasterRequest(int id, int userId, int requestId) {
//        this.id = id;
//        this.userId = userId;
//        this.requestId = requestId;
//    }
//
//    public MasterRequest(int userId, int requestId) {
//        this.userId = userId;
//        this.requestId = requestId;
//    }

    public MasterRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

//    public int getRequestId() {
//        return requestId;
//    }
//
//    public void setRequestId(int requestId) {
//        this.requestId = requestId;
//    }

    @Override
    public String toString() {
        return "MasterRequest{" +
                "id=" + id +
                ", user=" + user +
                ", request=" + request +
                '}';
    }
}
