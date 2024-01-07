package com.migrosone.couriertracking.service.impl;


import com.migrosone.couriertracking.enumaration.UserRole;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.User;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.repository.UserRepository;
import com.migrosone.couriertracking.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Implementation of {@link UserService}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CourierRepository courierRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAdminUser() {
        try {
            User admin = new User(
                    "admin",
                    "admin",
                    "admin@admin.com",
                    passwordEncoder.encode("admin"),
                    UserRole.ADMIN);
            userRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            log.info("Admin user already exists.");
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void handleUserCreation(User user) {
        user = userRepository.save(user);
        if (UserRole.COURIER.equals(user.getRole())) {
            Courier courier = new Courier();
            courier.setUser(user);
            courierRepository.save(courier);
        }
    }
}
