package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.payload.request.LoginRequest;
import com.migrosone.couriertracking.payload.request.RegisterRequest;
import com.migrosone.couriertracking.payload.response.GenericResponse;
import com.migrosone.couriertracking.payload.response.JwtResponse;
import com.migrosone.couriertracking.service.contract.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for Auth operations
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<JwtResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        GenericResponse<JwtResponse> response = GenericResponse.success(authService.register(registerRequest));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<JwtResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        GenericResponse<JwtResponse> response = GenericResponse.success(jwtResponse);
        return ResponseEntity.ok(response);
    }
}
