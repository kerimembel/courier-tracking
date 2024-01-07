package com.migrosone.couriertracking.util;

import com.migrosone.couriertracking.enumaration.DistanceUnit;

/**
 * Interface definition for Distance Calculator.
 * Can be used to define different Distance Calculator logics such as Vincenty or Haversine
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@FunctionalInterface
public interface DistanceCalculator {
    double calculateDistance(double x1, double y1, double x2, double y2, DistanceUnit targetUnit);
}
