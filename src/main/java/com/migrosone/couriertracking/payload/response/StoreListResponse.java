package com.migrosone.couriertracking.payload.response;

import com.migrosone.couriertracking.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Payload definition for returning a list of Stores.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
@AllArgsConstructor
public class StoreListResponse {
    private List<StoreDto> stores;
}
