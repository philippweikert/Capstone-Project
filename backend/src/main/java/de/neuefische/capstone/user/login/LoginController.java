package de.neuefische.capstone.user.login;

import de.neuefische.capstone.user.create.AppUser;
import de.neuefische.capstone.user.create.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    private List<String> getRoles(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    @PostMapping
    public Token login (@RequestBody Credentials loginUser){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getUsedPassword()));

            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", getRoles(authentication));
            Token newToken = new Token();
            newToken.setToken(jwtService.createToken(claims, loginUser.getUsername()));
            return newToken;

        }catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
        }
    }

    @GetMapping("/{me}")
    public ResponseEntity<AppUser> me (Principal principal){
        return ResponseEntity.of(userService.findByUsername(principal.getName()));
    }
}
