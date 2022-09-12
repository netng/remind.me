package com.training.alterra.miniproject.remindmeapp.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZoneId;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @JsonProperty("time_zone")
    private ZoneId timeZone;


}
