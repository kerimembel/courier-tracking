package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.dto.CourierEntryDto;
import com.migrosone.couriertracking.mapper.CourierEntryMapper;
import com.migrosone.couriertracking.repository.CourierEntryRepository;
import com.migrosone.couriertracking.service.contract.CourierEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link CourierEntryService}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Service
@RequiredArgsConstructor
public class CourierEntryServiceImpl implements CourierEntryService {

    private final CourierEntryRepository courierEntryRepository;

    private final CourierEntryMapper courierEntryMapper = CourierEntryMapper.INSTANCE;

    @Override
    public List<CourierEntryDto> getAllStoreEntriesForCourier(UUID courierId) {
        return courierEntryMapper.toDto(courierEntryRepository.findAllByCourierId(courierId));
    }

    @Override
    public List<CourierEntryDto> getStoreEntryForCourier(UUID courierId, UUID storeId) {
        return courierEntryMapper.toDto(courierEntryRepository.findAllByCourierIdAndStoreId(courierId, storeId));
    }
}
