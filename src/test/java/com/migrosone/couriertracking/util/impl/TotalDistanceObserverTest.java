package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.enumaration.DistanceUnit;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.repository.CourierRepository;
import com.migrosone.couriertracking.util.DistanceCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link TotalDistanceObserver}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class TotalDistanceObserverTest {

    @Mock
    private GeometryFactory geometryFactory;

    @Mock
    private DistanceCalculator distanceCalculator;

    @Mock
    private CourierRepository courierRepository;

    @InjectMocks
    private TotalDistanceObserver totalDistanceObserver;

    @Test
    public void testHandleLocationUpdate() {
        GeometryFactory localGeometryFactory = new GeometryFactory();
        Courier courier = new Courier();
        courier.setLastLocation(localGeometryFactory.createPoint(new Coordinate(0, 0)));
        double latitude = 40.9923307;
        double longitude = 29.1244229;
        LocalDateTime timestamp = LocalDateTime.now();

        when(distanceCalculator.calculateDistance(0, 0, latitude, longitude, DistanceUnit.KM)).thenReturn(50.0);

        totalDistanceObserver.handleLocationUpdate(courier, latitude, longitude, timestamp);

        verify(distanceCalculator, times(1)).calculateDistance(0, 0, latitude, longitude, DistanceUnit.KM);
        verify(geometryFactory, times(1)).createPoint(new Coordinate(latitude, longitude));
        verify(courierRepository, times(1)).save(courier);
    }
}