package com.cemiterio.Cemiterio.DeadUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface IDeadUserRepository extends JpaRepository<DeadUserModel, UUID> {
    DeadUserModel findByName(String name);
}
