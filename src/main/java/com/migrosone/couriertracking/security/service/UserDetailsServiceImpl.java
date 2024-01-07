package com.migrosone.couriertracking.security.service;

import com.migrosone.couriertracking.enumaration.UserRole;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.User;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Implementation of {@link UserDetailsService} for Authorization Operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final CourierRepository courierRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        UUID courierId = null;

        if (UserRole.COURIER.equals(user.getRole())) {
            Courier courier = courierRepository.findCourierByUser(user)
                    .orElseThrow(() -> new UsernameNotFoundException("Courier Not Found for user: " + user.getId()));
            courierId = courier.getId();
        }

        return UserDetailsImpl.build(user, courierId);
    }
}
