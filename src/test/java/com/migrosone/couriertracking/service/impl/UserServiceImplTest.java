package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.enumaration.UserRole;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.User;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link UserServiceImpl}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourierRepository courierRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void createAdminUser_Success() {
        when(passwordEncoder.encode("admin")).thenReturn("encodedAdminPassword");
        when(userRepository.save(any())).thenReturn(new User("admin", "admin", "admin@admin.com",
                "encodedAdminPassword", UserRole.ADMIN));

        assertDoesNotThrow(() -> userService.createAdminUser());
    }

    @Test
    public void createInitialCourier_Success() {
        when(passwordEncoder.encode("courier")).thenReturn("encodedCourierPassword");
        when(userRepository.save(any())).thenReturn(new User("courier", "courier", "courier@courier.com",
                "encodedCourierPassword", UserRole.COURIER));

        assertDoesNotThrow(() -> userService.createInitialCourier());
    }

    @Test
    public void existsByEmail_True() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        assertTrue(userService.existsByEmail("test@example.com"));
    }

    @Test
    public void existsByEmail_False() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

        assertFalse(userService.existsByEmail("test@example.com"));
    }

    @Test
    public void saveUser() {
        User user = new User("test", "password", "test@example.com", "encodedPassword", UserRole.COURIER);
        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userService.save(user));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void handleUserCreation_CourierUser() {
        User courierUser = new User("courier", "password", "courier@example.com", "encodedPassword", UserRole.COURIER);
        Courier courier = new Courier();

        when(userRepository.save(courierUser)).thenReturn(courierUser);
        when(courierRepository.save(any())).thenReturn(courier);

        userService.handleUserCreation(courierUser);

        verify(userRepository, times(1)).save(courierUser);
        verify(courierRepository, times(1)).save(any());
    }
}