package com.cemiterio.Cemiterio.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
    UserModel findByIduser(UUID userId);
    UserModel findByrole(String role);
}
