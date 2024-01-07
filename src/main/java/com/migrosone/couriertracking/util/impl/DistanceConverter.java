package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.enumaration.DistanceUnit;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used for converting distance units, with a default conversion factor for kilometers to miles.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
public class DistanceConverter {

    private static final Map<DistanceUnit, Double> CONVERSION_FACTORS = new HashMap<>();

    static {
        CONVERSION_FACTORS.put(DistanceUnit.MILE, 0.621371);
    }

    public double convertDistance(double distance, DistanceUnit targetUnit) {
        double conversionFactor = CONVERSION_FACTORS.getOrDefault(targetUnit, 1.0);
        return distance * conversionFactor;
    }
}
