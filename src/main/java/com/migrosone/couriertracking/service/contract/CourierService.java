package com.migrosone.couriertracking.service.contract;

import com.migrosone.couriertracking.dto.CourierDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface Definition for Courier Service to handle Courier Operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface CourierService {

    Double getTotalTravelDistance(UUID courierId);

    List<CourierDto> getAllCouriers();

    CourierDto getCourierById(UUID courierId);

    void deleteCourier(UUID courierId);

}
