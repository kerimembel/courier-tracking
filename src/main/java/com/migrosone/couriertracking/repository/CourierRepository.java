package com.migrosone.couriertracking.repository;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for {@link Courier} entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID> {

    Optional<Courier> findCourierById(UUID id);

    Optional<Courier> findCourierByUser(User user);

}
