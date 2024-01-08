package com.migrosone.couriertracking.payload.response;

import com.migrosone.couriertracking.dto.CourierEntryDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Payload definition for returning a list of Courier Entries.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
@AllArgsConstructor
public class CourierEntryListResponse {
    private List<CourierEntryDto> courierEntries;
}
