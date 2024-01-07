package com.migrosone.couriertracking.util;

import com.migrosone.couriertracking.model.Courier;

import java.time.LocalDateTime;

/**
 * Interface definition for Location Handling Strategy
 * This interface can be used to defined different strategies
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface StoreEntryStrategy {
    void checkStoreEntry(Courier courier, double latitude, double longitude, LocalDateTime timestamp);
}
