/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.dtos.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.alterra.miniproject.remindmeapp.entities.Role;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO implements Serializable {
    @JsonProperty("full_name")
    private String fullName;

    private String username;

    private String email;

    private String password;

    @JsonProperty("time_zone")
    private ZoneId timeZone;

    private Set<Role> roles = new HashSet<>();

    public UserRequestDTO(
            String fullName,
            String username,
            String email,
            String password,
            ZoneId timeZone
    ) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.timeZone = timeZone;
    }
}