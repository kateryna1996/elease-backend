package nl.klev.eleasebackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String username;

    @OneToOne
    @JsonIgnore
    private Account account;

    public User() {
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

    public Account getAccount() {
        return account;
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

    public void setAccount(Account account) {
        this.account = account;
    }
}
