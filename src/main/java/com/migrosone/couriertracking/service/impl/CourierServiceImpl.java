package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.exception.NotFoundException;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.service.contract.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link CourierService}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */


@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;

    @Override
    public Double getTotalTravelDistance(UUID courierId) {
        Courier courier = getCourierById(courierId);
        return courier.getTotalTravelDistance();
    }

    @Override
    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    @Override
    public Courier getCourierById(UUID courierId) {
        return courierRepository.findCourierById(courierId)
                .orElseThrow(() -> new NotFoundException(Courier.class, courierId));
    }

    @Override
    public void deleteCourier(UUID courierId) {
        courierRepository.deleteById(courierId);
    }
}
