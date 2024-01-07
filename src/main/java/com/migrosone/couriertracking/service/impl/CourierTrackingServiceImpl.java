package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.exception.NotFoundException;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.service.contract.CourierTrackingService;
import com.migrosone.couriertracking.util.CourierLocationObserver;
import com.migrosone.couriertracking.util.impl.EnterStoreObserver;
import com.migrosone.couriertracking.util.impl.TotalDistanceObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link CourierTrackingService}
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Service
public class CourierTrackingServiceImpl implements CourierTrackingService {

    private final CourierRepository courierRepository;

    private final List<CourierLocationObserver> locationObservers;

    @Autowired
    public CourierTrackingServiceImpl(
            CourierRepository courierRepository,
            EnterStoreObserver enterStoreObserver,
            TotalDistanceObserver totalDistanceObserver
    ) {

        this.courierRepository = courierRepository;
        this.locationObservers = Arrays.asList(enterStoreObserver, totalDistanceObserver);
    }

    @Override
    public void processCourierLocationUpdate(
            UUID courierId,
            double latitude,
            double longitude,
            LocalDateTime timestamp
    ) {
        Courier courier = getCourierById(courierId);
        notifyLocationObservers(courier, latitude, longitude, timestamp);
    }

    private void notifyLocationObservers(
            Courier courier,
            double latitude,
            double longitude,
            LocalDateTime timestamp
    ) {
        for (CourierLocationObserver observer : locationObservers) {
            observer.handleLocationUpdate(courier, latitude, longitude, timestamp);
        }
    }

    private Courier getCourierById(UUID courierId) {
        return courierRepository.findCourierById(courierId)
                .orElseThrow(() -> new NotFoundException(Courier.class, courierId));
    }
}
