package nl.klev.eleasebackend.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String username;

    public User() {
    }

    public User(Long id, String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
