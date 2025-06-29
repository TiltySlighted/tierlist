package com.valeevVA.tierlistBackend.controller.user;

import com.valeevVA.tierlistBackend.dto.UserDTO;
import com.valeevVA.tierlistBackend.dto.LoginRequestDTO;
import com.valeevVA.tierlistBackend.model.user.User;
import com.valeevVA.tierlistBackend.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword_hash(passwordEncoder.encode(userDTO.getPassword()));

            userService.saveUser(user);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка регистрации: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        User foundUser = userService.getUser().stream()
                .filter(u -> u.getUsername().equals(loginRequestDTO.getUsername()))
                .findFirst()
                .orElse(null);

        if (foundUser == null) {
            return ResponseEntity.status(401).body("Пользователь не найден");
        }

        // Проверяем пароль
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), foundUser.getPassword_hash())) {
            return ResponseEntity.status(401).body("Неверный пароль");
        }

        return ResponseEntity.ok(foundUser);
    }
}