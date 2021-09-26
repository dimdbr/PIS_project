package DTO;

public class RequestReview {
    private int id;
    private int userId;
    private int requestId;
    private String textReview;

    public RequestReview(int id, int userId, int requestId, String textReview) {
        this.id = id;
        this.userId = userId;
        this.requestId = requestId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    @Override
    public String toString() {
        return "RequestReview{" +
                "id=" + id +
                ", userId=" + userId +
                ", requestId=" + requestId +
                ", textReview='" + textReview + '\'' +
                '}';
    }
}
