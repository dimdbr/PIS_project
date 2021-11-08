package DTO;

import javax.persistence.*;

@Entity
@Table(name = "repair_request")
@NamedQuery(name = "Request.findByStatus",query = "select r from Request r where r.status.id=:status_id")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fix_price")
    private double fixPrice;
//    @Column(name = "status_id")
//    private int statusId;
    @Column(name = "user_id")
    private int userId;
    private String description;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")

    private Status status;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private MasterRequest masterRequest;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private RequestReview requestReview;

    public RequestReview getRequestReview() {
        return requestReview;
    }

    public void setRequestReview(RequestReview requestReview) {
        this.requestReview = requestReview;
    }

    public MasterRequest getMasterRequest() {
        return masterRequest;
    }

    public void setMasterRequest(MasterRequest masterRequest) {
        this.masterRequest = masterRequest;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Request(int id, double fixPrice, int userId, String description) {
        this.id = id;
        this.fixPrice = fixPrice;
        this.userId = userId;
        this.description = description;
    }

    public Request(double fixPrice, int userId, String description) {
        this.fixPrice = fixPrice;
        this.userId = userId;
        this.description = description;
    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFixPrice() {
        return fixPrice;
    }

    public void setFixPrice(double fixPrice) {
        this.fixPrice = fixPrice;
    }

    public int getStatusId() {
        return status.getId();
    }

    public void setStatusId(int statusId) {
        this.status.setId(statusId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", fixPrice=" + fixPrice +
//                ", statusId=" + statusId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                '}';
    }
}
