package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.enumaration.DistanceUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for {@link HaversineDistanceCalculator}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public class HaversineDistanceCalculatorTest {

    @Test
    public void testHaversineDistanceCalculator() {
        HaversineDistanceCalculator distanceCalculator = new HaversineDistanceCalculator(new DistanceConverter());
        //Ataşehir MMM Migros
        double lat1 = 40.9923307;
        double lon1 = 29.1244229;
        //Novada MMM Migros
        double lat2 = 40.986106;
        double lon2 = 29.1161293;

        double expectedDistanceInKm = 0.98;
        double expectedDistanceInMile = 0.61;

        double distanceInKm = distanceCalculator.calculateDistance(lat1, lon1, lat2, lon2, DistanceUnit.KM);
        double distanceInMile = distanceCalculator.calculateDistance(lat1, lon1, lat2, lon2, DistanceUnit.MILE);
        assertEquals(expectedDistanceInKm, distanceInKm, 0.1);
        assertEquals(expectedDistanceInMile, distanceInMile, 0.1);

    }
}