package com.migrosone.couriertracking.service.contract;

import com.migrosone.couriertracking.payload.request.RegisterRequest;
import com.migrosone.couriertracking.payload.response.JwtResponse;
import jakarta.transaction.Transactional;

/**
 * Interface definition for Auth Service.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface AuthService {

    JwtResponse authenticateUser(String email, String password);

    @Transactional
    JwtResponse register(RegisterRequest registerRequest);
}
