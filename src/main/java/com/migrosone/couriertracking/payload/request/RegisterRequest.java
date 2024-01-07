package com.migrosone.couriertracking.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Payload definition for register request.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "firstName cannot be blank")
    private String firstName;

    @NotBlank(message = "lastName cannot be blank")
    private String lastName;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
