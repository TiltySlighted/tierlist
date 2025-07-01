package com.valeevVA.tierlistBackend.controller.user;

import com.valeevVA.tierlistBackend.dto.LoginResponseDTO;
import com.valeevVA.tierlistBackend.dto.UserDTO;
import com.valeevVA.tierlistBackend.dto.LoginRequestDTO;
import com.valeevVA.tierlistBackend.model.user.User;
import com.valeevVA.tierlistBackend.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @PostMapping("/register") //успешно проверено в Postman
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

    @PostMapping("/login") //успешно проверено в Postman
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            User foundUser = userService.findByUsername(loginRequestDTO.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

            if (!passwordEncoder.matches(loginRequestDTO.getPassword(), foundUser.getPassword_hash())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный пароль");
            }
            // Преобразуем User в DTO перед возвратом
            LoginResponseDTO response = new LoginResponseDTO();
            response.setId(foundUser.getId_user());
            response.setUsername(foundUser.getUsername());
            response.setEmail(foundUser.getEmail());
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}