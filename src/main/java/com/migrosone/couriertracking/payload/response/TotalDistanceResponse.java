package com.migrosone.couriertracking.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Payload definition for returning the total distance of a courier.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
@AllArgsConstructor
public class TotalDistanceResponse {

    private Double totalTravelDistance;
}
