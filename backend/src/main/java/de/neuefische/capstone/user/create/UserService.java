package de.neuefische.capstone.user.create;

import de.neuefische.capstone.user.login.CreateUserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUser createUser (CreateUserCredentials createUserCredentials) {
        if (findByUsername(createUserCredentials.getUsername()).isPresent()){
            throw new IllegalArgumentException("Du bist schon registriert");
        }
        AppUser newAppUser = new AppUser();
        newAppUser.setUsername(createUserCredentials.getUsername());
        newAppUser.setRole("USER");
        newAppUser.setPassword(passwordEncoder.encode(createUserCredentials.getPassword()));
        return userRepo.save(newAppUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
