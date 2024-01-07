package com.migrosone.couriertracking.service.contract;

import com.migrosone.couriertracking.model.Store;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Interface Definition for Store Service to handle Store Operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public interface StoreService {

    @Transactional
    void loadStoreLocationsFromJson();

    List<Store> findStoresWithinDistance(double latitude, double longitude, double maxDistance);

}
