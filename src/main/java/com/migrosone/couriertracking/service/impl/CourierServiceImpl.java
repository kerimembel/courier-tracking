package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.dto.CourierDto;
import com.migrosone.couriertracking.dto.TotalDistanceDto;
import com.migrosone.couriertracking.exception.NotFoundException;
import com.migrosone.couriertracking.mapper.CourierMapper;
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
    private final CourierMapper courierMapper = CourierMapper.INSTANCE;

    @Override
    public TotalDistanceDto getTotalTravelDistance(UUID courierId) {
        Courier courier = courierRepository.findCourierById(courierId)
                .orElseThrow(() -> new NotFoundException(Courier.class, courierId));
        return new TotalDistanceDto(courier.getTotalTravelDistance(), courier.getDistanceUnit());
    }

    @Override
    public List<CourierDto> getAllCouriers() {
        return courierMapper.toDto(courierRepository.findAll());
    }

    @Override
    public CourierDto getCourierById(UUID courierId) {
        return courierMapper.toDto(courierRepository.findCourierById(courierId)
                .orElseThrow(() -> new NotFoundException(Courier.class, courierId)));
    }

    @Override
    public void deleteCourier(UUID courierId) {
        courierRepository.deleteById(courierId);
    }
}
