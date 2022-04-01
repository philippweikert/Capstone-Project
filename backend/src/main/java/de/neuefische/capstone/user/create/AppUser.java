package de.neuefische.capstone.user.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document (collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    private String userId;
    private String username;
    private String password;
    private String role;
}
