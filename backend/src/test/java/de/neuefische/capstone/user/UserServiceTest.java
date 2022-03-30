package de.neuefische.capstone.user;

import de.neuefische.capstone.user.create.AppUser;
import de.neuefische.capstone.user.create.UserRepo;
import de.neuefische.capstone.user.create.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class UserServiceTest {

    @Test
    void shouldCreateNewUser(){

        AppUser appUser = new AppUser();
        appUser.setUsername("Hans Hansen");
        appUser.setPassword("passwort1");
        appUser.setRole("User");

        UserRepo userRepo = Mockito.mock(UserRepo.class);
        UserService userService = new UserService(userRepo);

        userService.createUser(appUser);

        verify(userRepo).save(appUser);
    }
}