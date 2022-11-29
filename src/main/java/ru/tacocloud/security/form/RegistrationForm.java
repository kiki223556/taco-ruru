package ru.tacocloud.security.form;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tacocloud.model.auth.User;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    // creat a new User object
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }
}
