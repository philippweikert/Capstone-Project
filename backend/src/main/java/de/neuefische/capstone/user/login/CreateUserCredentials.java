package de.neuefische.capstone.user.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserCredentials {

    private String username;
    private String password;
    private String repeatPassword;

}
