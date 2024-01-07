package com.migrosone.couriertracking.service.contract;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Interface definition for Courier Tracking Service.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface CourierTrackingService {

    void processCourierLocationUpdate(
            UUID courierId,
            double latitude,
            double longitude,
            LocalDateTime timestamp
    );

}
