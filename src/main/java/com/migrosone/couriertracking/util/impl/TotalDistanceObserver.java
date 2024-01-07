package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.util.CourierLocationObserver;
import com.migrosone.couriertracking.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Implementation of {@link CourierLocationObserver} to update total distance of Couriers.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
@RequiredArgsConstructor
public class TotalDistanceObserver implements CourierLocationObserver {

    private final GeometryFactory geometryFactory;
    private final DistanceCalculator distanceCalculator;
    private final CourierRepository courierRepository;

    @Override
    public void handleLocationUpdate(
            Courier courier,
            double latitude,
            double longitude,
            LocalDateTime timestamp
    ) {
        if (courier.getLastLocation() != null) {
            double distance = distanceCalculator.calculateDistance(
                    courier.getLastLocation().getX(),
                    courier.getLastLocation().getY(),
                    latitude,
                    longitude,
                    courier.getDistanceUnit()
            );
            courier.setTotalTravelDistance(courier.getTotalTravelDistance() + distance);
        }

        courier.setLastLocation(geometryFactory.createPoint(new Coordinate(latitude, longitude)));
        courierRepository.save(courier);
    }
}
