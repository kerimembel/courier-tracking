package com.migrosone.couriertracking.service.contract;

import com.migrosone.couriertracking.dto.CourierEntryDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface Definition for Courier Entry Service to retrieve Courier Entries.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface CourierEntryService {

    List<CourierEntryDto> getAllStoreEntriesForCourier(UUID courierId);

    List<CourierEntryDto> getStoreEntryForCourier(UUID courierId, UUID storeId);
}
