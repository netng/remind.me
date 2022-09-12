/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.dtos.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@EqualsAndHashCode
public class UserResponseDTO implements Serializable {
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String username;

    private String email;

    @JsonProperty("time_zone")
    private ZoneId timeZone;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedAt;
}