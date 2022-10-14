/**
 * @Author Nandang Sopyan
 * @ApplicationName remind.me app
 * @CreatedAt Sept 2022
 * @Description This is a REST API application as mini project task at alterra training academy program
 */
package com.training.alterra.miniproject.remindmeapp.payloads;

import com.training.alterra.miniproject.remindmeapp.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse<T> {
    private String accessToken;
    private String tokenType = "Bearer";
    private T user;

    public JwtAuthenticationResponse(String accessToken, T user) {
        this.accessToken = accessToken;
        this.user = user;

    }
}
