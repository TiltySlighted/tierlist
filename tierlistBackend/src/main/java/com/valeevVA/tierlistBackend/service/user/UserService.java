package com.valeevVA.tierlistBackend.service.user;

import com.valeevVA.tierlistBackend.model.user.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public List<User> getUser();
    Optional<User> findByUsername(String username); // Добавляем новый метод
}
