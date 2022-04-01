package de.neuefische.capstone.user.create;

import de.neuefische.capstone.user.login.CreatedUserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users/createuser")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public AppUser createUser (@RequestBody CreatedUserCredentials createCredentials) {
        if(!createCredentials.getPassword().equals(createCredentials.getRepeatPassword())){
            throw new IllegalArgumentException("Passwörter stimmen nicht überein");
        }
        createCredentials.setPassword(passwordEncoder.encode(createCredentials.getPassword()));
        return userService.createUser(createCredentials);
    }

}
