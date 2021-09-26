package DTO;

public class MasterRequest {
    private int id;
    private int userId;
    private int requestId;

    public MasterRequest(int id, int userId, int requestId) {
        this.id = id;
        this.userId = userId;
        this.requestId = requestId;
    }

    public MasterRequest() {
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

    @Override
    public String toString() {
        return "MasterRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", requestId=" + requestId +
                '}';
    }
}
