package com.migrosone.couriertracking.dto;

import com.migrosone.couriertracking.enumaration.DistanceUnit;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing Total Distance information.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
@AllArgsConstructor
public class TotalDistanceDto {

    private Double totalTravelDistance;
    private DistanceUnit distanceUnit;
}
