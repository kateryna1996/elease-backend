package nl.klev.eleasebackend.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserInputDto {

    @NotNull
    public String username;
    @NotNull
    public String password;
    public Boolean enabled;
    public String apikey;
    @NotNull
    @Email
    public String email;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getApikey() {
        return apikey;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
