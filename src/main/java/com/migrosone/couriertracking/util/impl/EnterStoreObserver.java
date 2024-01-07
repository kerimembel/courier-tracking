package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.util.CourierLocationObserver;
import com.migrosone.couriertracking.util.StoreEntryStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Implementation of {@link CourierLocationObserver} to checking store entries
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
@RequiredArgsConstructor
public class EnterStoreObserver implements CourierLocationObserver {

    private final StoreEntryStrategy storeEntryStrategy;

    @Override
    public void handleLocationUpdate(
            Courier courier,
            double latitude,
            double longitude,
            LocalDateTime timestamp
    ) {
        storeEntryStrategy.checkStoreEntry(courier, latitude, longitude, timestamp);
    }
}
