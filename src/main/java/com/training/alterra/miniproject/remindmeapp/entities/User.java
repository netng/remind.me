package com.training.alterra.miniproject.remindmeapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "users")
@EntityListeners(AutoCloseable.class)
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
