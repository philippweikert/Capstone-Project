package de.neuefische.capstone.user;

import de.neuefische.capstone.user.create.AppUser;
import de.neuefische.capstone.user.create.UserRepo;
import de.neuefische.capstone.user.create.UserService;
import de.neuefische.capstone.user.login.CreatedUserCredentials;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserServiceTest {

    @Test
    void shouldCreateNewUser() {

        CreatedUserCredentials createdUserCredentials =
                new CreatedUserCredentials("Hans Hansen", "passwort1", "passwort1");

        AppUser newAppUser = new AppUser();
        newAppUser.setUsername("Hans Hansen");
        newAppUser.setPassword("Hans1");
        newAppUser.setRole("USER");

        AppUser savedAppUser = new AppUser();
        savedAppUser.setUserId("0815");
        savedAppUser.setUsername("Hans Hansen");
        savedAppUser.setPassword("Hans1");
        savedAppUser.setRole("USER");

        UserRepo userRepo = Mockito.mock(UserRepo.class);
        when(userRepo.save(newAppUser)).thenReturn(savedAppUser);

        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(passwordEncoder.encode("passwort1")).thenReturn("Hans1");

        UserService userService = new UserService(userRepo, passwordEncoder);
        AppUser actual = userService.createUser(createdUserCredentials);

        assertThat(actual).isSameAs(savedAppUser);
    }

    @Test
    void shouldFindUserByName(){
        AppUser appUser = new AppUser("0815", "Hans Hansen", "Hans1", "USER");

        UserRepo userRepo = Mockito.mock(UserRepo.class);
        when(userRepo.findByUsername("Hans Hansen")).thenReturn(Optional.of(appUser));

        UserService userService = new UserService(userRepo, mock(PasswordEncoder.class));

        assertThat(userService.findByUsername("Hans Hansen")).contains(appUser);
    }
}