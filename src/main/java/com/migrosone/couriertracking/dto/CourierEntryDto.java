package com.migrosone.couriertracking.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing CourierEntry information.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
public class CourierEntryDto {
    private UUID id;
    private UUID storeId;
    private UUID courierId;
    private LocalDateTime entryTime;
}
