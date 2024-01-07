package com.migrosone.couriertracking.util;

import com.migrosone.couriertracking.model.Courier;

import java.time.LocalDateTime;

/**
 * Interface definition for Courier Location Observer
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface CourierLocationObserver {

    void handleLocationUpdate(Courier courier, double latitude,
                              double longitude, LocalDateTime timestamp);
}
