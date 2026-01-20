package com.pjrminis.diecast.controller;

import com.pjrminis.diecast.config.JwtUtil;
import com.pjrminis.diecast.dto.AuthDto;
import com.pjrminis.diecast.dto.AuthDtoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    //private UserService userService;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthDtoResponse login(@RequestBody AuthDto authDto) {
        // Aqui você pode validar o usuário (ex.: consultar no banco)@Autowired
        AuthDtoResponse response = new AuthDtoResponse();
        response.setToken(jwtUtil.generateToken(authDto.getUsername()));
        response.setExpiresIn(jwtUtil.getExpirationTimeInSeconds());
        return response;
    }
/*
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (userService.isValidUser(username, password)) {
            return ResponseEntity.ok(jwtUtil.generateToken(username));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }
*/

}
