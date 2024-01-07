package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.enumaration.UserRole;
import com.migrosone.couriertracking.exception.UserAlreadyExistsException;
import com.migrosone.couriertracking.model.User;
import com.migrosone.couriertracking.payload.request.RegisterRequest;
import com.migrosone.couriertracking.payload.response.JwtResponse;
import com.migrosone.couriertracking.security.jwt.JwtUtils;
import com.migrosone.couriertracking.security.service.UserDetailsImpl;
import com.migrosone.couriertracking.service.contract.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuthService}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse authenticateUser(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserRole userRole = UserRole.valueOf(userDetails.getAuthorities().iterator().next().getAuthority());

        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getCourierId(),
                userDetails.getEmail(),
                userRole);
    }

    @Override
    public JwtResponse register(RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException(registerRequest.getEmail());
        }

        User user = new User(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                UserRole.COURIER
        );

        userService.handleUserCreation(user);
        return authenticateUser(registerRequest.getEmail(), registerRequest.getPassword());
    }
}
