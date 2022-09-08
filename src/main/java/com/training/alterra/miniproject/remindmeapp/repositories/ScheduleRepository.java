package com.training.alterra.miniproject.remindmeapp.repositories;

import com.training.alterra.miniproject.remindmeapp.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
