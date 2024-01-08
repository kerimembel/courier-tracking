package com.migrosone.couriertracking.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.migrosone.couriertracking.util.impl.JsonToPointDeserializer;
import com.migrosone.couriertracking.util.impl.PointToJsonSerializer;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing Store information.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
public class StoreDto {

    private UUID id;

    private String name;

    @JsonSerialize(using = PointToJsonSerializer.class)
    @JsonDeserialize(using = JsonToPointDeserializer.class)
    private Point location;
}
