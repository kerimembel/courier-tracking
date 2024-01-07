package com.migrosone.couriertracking.payload.response;

import com.migrosone.couriertracking.enumaration.UserRole;
import lombok.Data;

import java.util.UUID;

/**
 * Payload definition for returning a Token response.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
public class JwtResponse {

    public static final String TOKEN_TYPE = "Bearer";

    private String token;
    private String tokenType = TOKEN_TYPE;
    private UUID id;
    private UUID courierId;
    private String email;
    private UserRole role;

    public JwtResponse(String jwt, UUID userId, UUID courierId, String email, UserRole role) {
        this.token = jwt;
        this.id = userId;
        this.courierId = courierId;
        this.email = email;
        this.role = role;
    }
}