package com.pjrminis.diecast.controller;

import com.pjrminis.diecast.dto.UserFormDto;
import com.pjrminis.diecast.dto.UserWithVehiclesDto;
import com.pjrminis.diecast.model.User;
import com.pjrminis.diecast.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUsersController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserWithVehiclesDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserFormDto formDto) {
        User user = userService.createUser(formDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody UserFormDto formDto) {
        User user = userService.updateUser(id, formDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-username/{username}")
    public ResponseEntity<Map<String, Boolean>> checkUsernameAvailable(
            @PathVariable String username,
            @RequestParam(required = false) Long excludeId) {
        boolean available = userService.isUsernameAvailable(username, excludeId);
        return ResponseEntity.ok(Map.of("available", available));
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailAvailable(
            @PathVariable String email,
            @RequestParam(required = false) Long excludeId) {
        boolean available = userService.isEmailAvailable(email, excludeId);
        return ResponseEntity.ok(Map.of("available", available));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getAvailableRoles() {
        return ResponseEntity.ok(userService.getAvailableRoles());
    }
}
