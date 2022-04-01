package de.neuefische.capstone.user.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatedUserCredentials {

    private String username;
    private String password;
    private String repeatPassword;

}
