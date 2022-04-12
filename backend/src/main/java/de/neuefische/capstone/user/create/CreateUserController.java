package de.neuefische.capstone.user.create;

import de.neuefische.capstone.user.login.CreateUserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/createuser")
@RequiredArgsConstructor
@CrossOrigin
public class CreateUserController {

    private final UserService userService;

    @PostMapping
    public AppUser createUser (@RequestBody CreateUserCredentials createCredentials) {
        if(!createCredentials.getPassword().equals(createCredentials.getRepeatPassword())){
            throw new IllegalArgumentException("Passwörter stimmen nicht überein");
        }
        createCredentials.setPassword(createCredentials.getPassword());
        return userService.createUser(createCredentials);
    }

}
