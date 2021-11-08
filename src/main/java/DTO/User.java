package DTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findUserRole",query = "select ur from UserRole ur " +
        "where ur.userId=:user_id and ur.roleId=:role_id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String first_name;
    private String last_name;
    private String login;
    private String password;

    public List<Role> getRoleList() {
        return roles;
    }

    public void setRoleList(List<Role> roleList) {
        this.roles = roleList;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )

    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Request> requests;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MasterRequest> takenRequests;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RequestReview> reviewedRequests;

    public List<RequestReview> getReviewedRequests() {
        return reviewedRequests;
    }

    public void setReviewedRequests(List<RequestReview> reviewedRequests) {
        this.reviewedRequests = reviewedRequests;
    }

    public List<MasterRequest> getTakenRequests() {
        return takenRequests;
    }

    public void setTakenRequests(List<MasterRequest> takenRequests) {
        this.takenRequests = takenRequests;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public User(int id, String first_name, String last_name, String login, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
    }

    public User(String first_name, String last_name, String login, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
