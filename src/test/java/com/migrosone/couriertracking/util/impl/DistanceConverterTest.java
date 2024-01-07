package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.enumaration.DistanceUnit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for {@link DistanceConverter}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public class DistanceConverterTest {

    @Test
    public void testConvertDistance() {
        DistanceConverter distanceConverter = new DistanceConverter();

        double distanceInKm = 10.0;
        double expectedDistanceInMile = 6.21371;

        double convertedDistanceInMile = distanceConverter.convertDistance(distanceInKm, DistanceUnit.MILE);
        double convertedDistanceInKm = distanceConverter.convertDistance(distanceInKm, DistanceUnit.KM);

        assertEquals(expectedDistanceInMile, convertedDistanceInMile, 0.00001);
        assertEquals(convertedDistanceInKm, distanceInKm, 0.00001);
    }
}
