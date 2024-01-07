package com.migrosone.couriertracking.service.impl;


import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.util.impl.EnterStoreObserver;
import com.migrosone.couriertracking.util.impl.TotalDistanceObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link CourierTrackingServiceImpl}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class CourierTrackingServiceImplTest {

    @Mock
    private CourierRepository courierRepository;

    @Mock
    private EnterStoreObserver enterStoreObserver;

    @Mock
    private TotalDistanceObserver totalDistanceObserver;

    private CourierTrackingServiceImpl courierTrackingService;

    @Before
    public void setUp() {
        courierTrackingService = new CourierTrackingServiceImpl(
                courierRepository,
                enterStoreObserver,
                totalDistanceObserver
        );
    }

    @Test
    public void testProcessCourierLocationUpdate() {
        UUID courierId = UUID.randomUUID();
        double latitude = 40.7128;
        double longitude = -74.0060;
        LocalDateTime timestamp = LocalDateTime.now();

        Courier courier = new Courier();
        courier.setId(courierId);
        when(courierRepository.findCourierById(courierId)).thenReturn(java.util.Optional.of(courier));

        courierTrackingService.processCourierLocationUpdate(courierId, latitude, longitude, timestamp);

        verify(courierRepository, times(1)).findCourierById(courierId);
        verify(enterStoreObserver, times(1)).handleLocationUpdate(courier, latitude, longitude, timestamp);
        verify(totalDistanceObserver, times(1)).handleLocationUpdate(courier, latitude, longitude, timestamp);
    }
}
