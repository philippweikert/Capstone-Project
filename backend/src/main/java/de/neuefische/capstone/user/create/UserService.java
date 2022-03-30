package de.neuefische.capstone.user.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    public AppUser createUser (AppUser appUser) {
        return userRepo.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
