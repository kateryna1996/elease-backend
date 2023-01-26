package nl.klev.eleasebackend.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserInputDto {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String username;

    public UserInputDto() {
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
