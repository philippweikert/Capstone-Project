package de.neuefische.capstone.user.create;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/plants/createuser")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public AppUser createUser (@RequestBody AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userService.createUser(appUser);
    }

    @GetMapping("/{me}")
    public ResponseEntity<AppUser> me (Principal principal){
        return ResponseEntity.of(userService.findByUsername(principal.getName()));
    }
}
