package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.CourierEntry;
import com.migrosone.couriertracking.model.Store;
import com.migrosone.couriertracking.repository.CourierEntryRepository;
import com.migrosone.couriertracking.service.contract.StoreService;
import com.migrosone.couriertracking.util.StoreEntryStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation for {@link StoreEntryStrategy}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
@RequiredArgsConstructor
public class StoreEntryStrategyImpl implements StoreEntryStrategy {

    private static final double MAX_DISTANCE = 100.0;

    private final StoreService storeService;
    private final CourierEntryRepository courierEntryRepository;

    @Override
    public void checkStoreEntry(Courier courier, double latitude, double longitude, LocalDateTime timestamp) {
        List<Store> nearStores = storeService.findStoresWithinDistance(latitude, longitude, MAX_DISTANCE);

        for (Store store : nearStores) {
            boolean isEntered = checkCourierEntered(store, courier, timestamp);

            if (isEntered) {
                saveCourierEntry(courier, store, timestamp);
            }
        }
    }

    private boolean checkCourierEntered(
            Store store,
            Courier courier,
            LocalDateTime timestamp
    ) {
        LocalDateTime oneMinuteAgo = timestamp.minusMinutes(1);
        boolean hasEnteredWithinLastMinute = courierEntryRepository.existsByCourierAndStoreAndEntryTimeAfter(
                courier,
                store,
                oneMinuteAgo
        );
        return !hasEnteredWithinLastMinute;
    }

    private void saveCourierEntry(
            Courier courier,
            Store store,
            LocalDateTime timestamp
    ) {
        CourierEntry courierEntry = new CourierEntry();
        courierEntry.setCourier(courier);
        courierEntry.setStore(store);
        courierEntry.setEntryTime(timestamp);
        courierEntryRepository.save(courierEntry);
    }
}
