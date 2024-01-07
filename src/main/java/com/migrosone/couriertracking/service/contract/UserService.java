package com.migrosone.couriertracking.service.contract;


import com.migrosone.couriertracking.model.User;

/**
 * Interface Definition for User Service to handle User Operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface UserService {
    void createAdminUser();

    void createInitialCourier();

    boolean existsByEmail(String email);

    User save(User user);

    void handleUserCreation(User user);
}
