package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.enumaration.DistanceUnit;
import com.migrosone.couriertracking.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link DistanceCalculator} using Haversine method.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Primary
@Component
@RequiredArgsConstructor
public class HaversineDistanceCalculator implements DistanceCalculator {

    private static final double EARTH_RADIUS = 6371.0;
    private final DistanceConverter distanceConverter;

    @Override
    public double calculateDistance(double x1, double y1, double x2, double y2, DistanceUnit targetUnit) {
        double distanceInKm = calculateDistanceInKm(x1, y1, x2, y2);
        return distanceConverter.convertDistance(distanceInKm, targetUnit);
    }

    public double calculateDistanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}
