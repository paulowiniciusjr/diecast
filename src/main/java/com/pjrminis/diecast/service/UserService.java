package com.pjrminis.diecast.service;

import com.pjrminis.diecast.dto.UserFormDto;
import com.pjrminis.diecast.dto.UserWithVehiclesDto;
import com.pjrminis.diecast.enums.Role;
import com.pjrminis.diecast.model.User;
import com.pjrminis.diecast.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Usuário já existe"
            );
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public List<UserWithVehiclesDto> getAllUsers() {
        return userRepository.findAllUsersWithVehicleCounts();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));
    }

    public User createUser(UserFormDto formDto) {
        if (userRepository.findByUsername(formDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Usuário já existe"
            );
        }

        if (userRepository.findByEmail(formDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email já existe"
            );
        }

        User user = new User();
        user.setUsername(formDto.getUsername());
        user.setEmail(formDto.getEmail());
        user.setPhone(formDto.getPhone());
        user.setPassword(passwordEncoder.encode(formDto.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public User updateUser(Long id, UserFormDto formDto) {
        User user = getUserById(id);

        if (!user.getUsername().equals(formDto.getUsername()) &&
                userRepository.findByUsername(formDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Usuário já existe"
            );
        }

        if (!user.getEmail().equals(formDto.getEmail()) &&
                userRepository.findByEmail(formDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email já existe"
            );
        }

        user.setUsername(formDto.getUsername());
        user.setEmail(formDto.getEmail());
        user.setPhone(formDto.getPhone());

        if (formDto.getRole() != null && !formDto.getRole().isEmpty()) {
            user.setRole(Role.valueOf(formDto.getRole()));
        }

        if (formDto.getPassword() != null && !formDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(formDto.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public boolean isUsernameAvailable(String username, Long excludeId) {
        var existing = userRepository.findByUsername(username);
        if (existing.isEmpty()) {
            return true;
        }
        return existing.get().getId().equals(excludeId);
    }

    public boolean isEmailAvailable(String email, Long excludeId) {
        var existing = userRepository.findByEmail(email);
        if (existing.isEmpty()) {
            return true;
        }
        return existing.get().getId().equals(excludeId);
    }

    public List<String> getAvailableRoles() {
        return Arrays.stream(Role.values())
                .map(Role::name)
                .toList();
    }
}

