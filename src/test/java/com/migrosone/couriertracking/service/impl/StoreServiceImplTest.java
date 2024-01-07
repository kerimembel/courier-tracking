package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link StoreServiceImpl}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class StoreServiceImplTest {

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private GeometryFactory geometryFactory;

    @InjectMocks
    private StoreServiceImpl storeService;

    @Test
    public void testLoadStoreLocationsFromJson_Success() {
        when(storeRepository.saveAll(anyList())).thenReturn(null);
        when(geometryFactory.createPoint(any(Coordinate.class))).thenReturn(null);
        storeService.loadStoreLocationsFromJson();

        verify(storeRepository, times(1)).saveAll(anyList());
        verify(geometryFactory, atLeastOnce()).createPoint(any(Coordinate.class));
    }

    @Test
    public void testLoadStoreLocationsFromJson_DataIntegrityViolation() {
        when(storeRepository.saveAll(anyList())).thenThrow(DataIntegrityViolationException.class);
        storeService.loadStoreLocationsFromJson();

        verify(storeRepository, times(1)).saveAll(anyList());
    }
}