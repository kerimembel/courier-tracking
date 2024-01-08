package com.migrosone.couriertracking.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.migrosone.couriertracking.enumaration.DistanceUnit;
import com.migrosone.couriertracking.util.impl.JsonToPointDeserializer;
import com.migrosone.couriertracking.util.impl.PointToJsonSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing Courier information.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
public class CourierDto {

    private UUID id;

    private UUID userId;

    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    private Point lastLocation;

    private Double totalTravelDistance;

    private DistanceUnit distanceUnit;
}
