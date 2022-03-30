package de.neuefische.capstone.user.create;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document (collection = "users")
@Data
public class AppUser {

    @Id
    private String userId;
    private String username;
    private String password;
    private String role;
}
