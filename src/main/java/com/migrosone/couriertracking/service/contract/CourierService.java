package com.migrosone.couriertracking.service.contract;

import com.migrosone.couriertracking.model.Courier;

import java.util.List;
import java.util.UUID;

/**
 * Interface Definition for Courier Service to handle Role Operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface CourierService {

    Double getTotalTravelDistance(UUID courierId);

    List<Courier> getAllCouriers();

    Courier getCourierById(UUID courierId);

    void deleteCourier(UUID courierId);

}
