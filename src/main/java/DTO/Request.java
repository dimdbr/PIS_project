package DTO;

public class Request {
    private int id;
    private double fixPrice;
    private int statusId;
    private int userId;
    private String description;

    public Request(int id, double fixPrice, int statusId, int userId, String description) {
        this.id = id;
        this.fixPrice = fixPrice;
        this.statusId = statusId;
        this.userId = userId;
        this.description = description;
    }

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
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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
                ", statusId=" + statusId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                '}';
    }
}
