package com.migrosone.couriertracking.payload.response;

import com.migrosone.couriertracking.dto.TotalDistanceDto;
import com.migrosone.couriertracking.enumaration.DistanceUnit;
import lombok.Data;

/**
 * Payload definition for returning the total distance of a courier.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
public class TotalDistanceResponse {

    private Double totalTravelDistance;
    private DistanceUnit distanceUnit;

    public TotalDistanceResponse(TotalDistanceDto dto) {
        this.totalTravelDistance = dto.getTotalTravelDistance();
        this.distanceUnit = dto.getDistanceUnit();
    }
}
