package com.pjrminis.diecast.controller;

import com.pjrminis.diecast.config.JwtUtil;
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
    public String login(@RequestParam String username) {
        // Aqui você pode validar o usuário (ex.: consultar no banco)@Autowired
        return jwtUtil.generateToken(username);
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
