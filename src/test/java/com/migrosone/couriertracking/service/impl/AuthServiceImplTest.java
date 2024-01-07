package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.enumaration.UserRole;
import com.migrosone.couriertracking.exception.UserAlreadyExistsException;
import com.migrosone.couriertracking.payload.request.RegisterRequest;
import com.migrosone.couriertracking.payload.response.JwtResponse;
import com.migrosone.couriertracking.security.jwt.JwtUtils;
import com.migrosone.couriertracking.security.service.UserDetailsImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link AuthServiceImpl}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    public void authenticateUser_Success() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));
        when(jwtUtils.generateToken(any(Authentication.class))).thenReturn("mockedJwtToken");

        UserDetailsImpl userDetails = new UserDetailsImpl(
                UUID.randomUUID(),
                "test@test.com",
                "encodedPassword",
                UserRole.COURIER,
                UUID.randomUUID()
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        JwtResponse jwtResponse = authService.authenticateUser("test@test.com", "password");

        assertNotNull(jwtResponse);
        assertEquals("mockedJwtToken", jwtResponse.getToken());
    }

    @Test
    public void register_Success() {
        RegisterRequest registerRequest = new RegisterRequest("TEST", "TEST", "test@test.com", "password");

        when(userService.existsByEmail(registerRequest.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");

        UserDetailsImpl userDetails = new UserDetailsImpl(
                UUID.randomUUID(),
                "test@test.com",
                "encodedPassword",
                UserRole.COURIER,
                UUID.randomUUID()
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        JwtResponse jwtResponse = authService.register(registerRequest);

        assertNotNull(jwtResponse);
    }

    @Test
    public void register_UserAlreadyExists() {
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "test@example.com", "password");

        when(userService.existsByEmail(registerRequest.getEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> authService.register(registerRequest));
    }
}
