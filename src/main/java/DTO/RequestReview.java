package DTO;

import javax.persistence.*;

@Entity
@Table(name = "request_review")
public class RequestReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;
    @Column(name = "text_review")
    private String textReview;

    public RequestReview(int id, int userId, int requestId, String textReview) {
        this.id = id;
        this.textReview = textReview;
    }

    public RequestReview() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public int getRequestId() {
//        return requestId;
//    }
//
//    public void setRequestId(int requestId) {
//        this.requestId = requestId;
//    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }


}
