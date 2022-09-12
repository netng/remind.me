package com.training.alterra.miniproject.remindmeapp.repositories;

import com.training.alterra.miniproject.remindmeapp.entities.Role;
import com.training.alterra.miniproject.remindmeapp.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
