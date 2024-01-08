package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.dto.CourierDto;
import com.migrosone.couriertracking.mapper.CourierMapper;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.repository.CourierRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link CourierServiceImpl}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class CourierServiceImplTest {

    private final CourierMapper courierMapper = CourierMapper.INSTANCE;

    @Mock
    private CourierRepository courierRepository;

    @InjectMocks
    private CourierServiceImpl courierService;

    @Test
    public void testGetTotalTravelDistance() {
        UUID courierId = UUID.randomUUID();
        Courier courier = new Courier();
        courier.setTotalTravelDistance(100.0);

        when(courierRepository.findCourierById(courierId)).thenReturn(Optional.of(courier));

        Double totalTravelDistance = courierService.getTotalTravelDistance(courierId);

        assertEquals(100.0, totalTravelDistance, 0.001);

        verify(courierRepository, times(1)).findCourierById(courierId);
    }

    @Test
    public void testGetAllCouriers() {
        Courier courier1 = new Courier();
        Courier courier2 = new Courier();
        List<Courier> expectedCouriers = Arrays.asList(courier1, courier2);

        when(courierRepository.findAll()).thenReturn(expectedCouriers);

        List<CourierDto> actualCouriers = courierService.getAllCouriers();

        List<CourierDto> expectedCourierDtoList = expectedCouriers.stream()
                .map(courierMapper::toDto)
                .toList();

        assertEquals(expectedCourierDtoList, actualCouriers);

        verify(courierRepository, times(1)).findAll();
    }

    @Test
    public void testGetCourierById() {
        UUID courierId = UUID.randomUUID();
        Courier expectedCourier = new Courier();

        when(courierRepository.findCourierById(courierId)).thenReturn(Optional.of(expectedCourier));

        CourierDto actualCourier = courierService.getCourierById(courierId);

        assertEquals(courierMapper.toDto(expectedCourier), actualCourier);

        verify(courierRepository, times(1)).findCourierById(courierId);
    }

    @Test
    public void testDeleteCourier() {
        UUID courierId = UUID.randomUUID();
        courierService.deleteCourier(courierId);

        verify(courierRepository, times(1)).deleteById(courierId);
    }
}