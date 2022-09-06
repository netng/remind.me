package com.training.alterra.miniproject.remindmeapp.dtos.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO implements Serializable {
    @JsonProperty("full_name")
    private String fullName;

    private String email;

    private String password;
}
