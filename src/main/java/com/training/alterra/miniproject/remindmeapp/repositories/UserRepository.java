package com.training.alterra.miniproject.remindmeapp.repositories;

import com.training.alterra.miniproject.remindmeapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
