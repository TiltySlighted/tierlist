package com.valeevVA.tierlistBackend.repository.user;

import com.valeevVA.tierlistBackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
