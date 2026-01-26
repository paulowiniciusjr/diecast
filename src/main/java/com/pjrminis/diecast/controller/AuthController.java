package com.pjrminis.diecast.controller;

import com.pjrminis.diecast.config.JwtUtil;
import com.pjrminis.diecast.dto.*;
import com.pjrminis.diecast.model.User;
import com.pjrminis.diecast.repository.UserRepository;
import com.pjrminis.diecast.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDtoResponse> login(@RequestBody AuthDto authDto) {

        User user = userRepository.findByUsername(authDto.getUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Usuário inválido"
                ));

        if (!passwordEncoder.matches(authDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Senha inválida"
            );
        }

        AuthDtoResponse response = new AuthDtoResponse();
        response.setToken(jwtUtil.generateToken(user.getUsername(), user.getRole().name()));
        response.setExpiresIn(jwtUtil.getExpirationTimeInSeconds());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/me")
    public ResponseEntity<AuthMeResponse> me(Authentication authentication) {

        String username = authentication.getName();

        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("USER");

        return ResponseEntity.ok(
                new AuthMeResponse(username, role)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request) {

        User user = userService.register(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getRole().name()
                ));
    }



}
