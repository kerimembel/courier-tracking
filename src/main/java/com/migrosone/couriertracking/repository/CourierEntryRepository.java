package com.migrosone.couriertracking.repository;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.CourierEntry;
import com.migrosone.couriertracking.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Repository for {@link CourierEntry} entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Repository
public interface CourierEntryRepository extends JpaRepository<CourierEntry, UUID> {

    boolean existsByCourierAndStoreAndEntryTimeAfter(Courier courier, Store store, LocalDateTime timestamp);
}
