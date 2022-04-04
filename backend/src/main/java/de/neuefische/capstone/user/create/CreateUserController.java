package de.neuefische.capstone.user.create;

import de.neuefische.capstone.user.login.CreateUserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/createuser")
@RequiredArgsConstructor
@CrossOrigin
public class CreateUserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public AppUser createUser (@RequestBody CreateUserCredentials createCredentials) {
        if(!createCredentials.getPassword().equals(createCredentials.getRepeatPassword())){
            throw new IllegalArgumentException("Passwörter stimmen nicht überein");
        }
        createCredentials.setPassword(passwordEncoder.encode(createCredentials.getPassword()));
        return userService.createUser(createCredentials);
    }

}
