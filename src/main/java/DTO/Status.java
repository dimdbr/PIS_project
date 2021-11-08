package DTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status" )
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String status;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Request> repair_request;
    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public List<Request> getRequests() {
        return repair_request;
    }

    public void setRequests(List<Request> requests) {
        this.repair_request = requests;
    }

    public Status(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Status() {
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
