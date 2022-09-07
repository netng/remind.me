package com.training.alterra.miniproject.remindmeapp.repositories;

import com.training.alterra.miniproject.remindmeapp.entities.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
