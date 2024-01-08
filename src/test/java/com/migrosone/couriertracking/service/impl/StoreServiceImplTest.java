package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.dto.StoreDto;
import com.migrosone.couriertracking.mapper.StoreMapper;
import com.migrosone.couriertracking.model.Store;
import com.migrosone.couriertracking.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

    private final StoreMapper storeMapper = StoreMapper.INSTANCE;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private GeometryFactory geometryFactory;

    @InjectMocks
    private StoreServiceImpl storeService;

    @Test
    public void testFindStoresWithinDistance() {
        double latitude = 40.9923307;
        double longitude = 29.1244229;
        double maxDistance = 10.0;
        Store store1 = new Store();
        Store store2 = new Store();
        List<Store> expectedStores = Arrays.asList(store1, store2);

        when(storeRepository.findStoresWithinDistance(latitude, longitude, maxDistance))
                .thenReturn(expectedStores);

        List<Store> actualStores = storeService.findStoresWithinDistance(latitude, longitude, maxDistance);

        assertEquals(expectedStores, actualStores);

        verify(storeRepository, times(1)).findStoresWithinDistance(latitude, longitude, maxDistance);
    }

    @Test
    public void testGetAllStores() {
        Store store1 = new Store();
        Store store2 = new Store();
        List<Store> expectedStores = Arrays.asList(store1, store2);
        List<StoreDto> expectedStoreDtoList = storeMapper.toDto(expectedStores);

        when(storeRepository.findAll()).thenReturn(expectedStores);

        List<StoreDto> actualStoreDtoList = storeService.getAllStores();

        assertEquals(expectedStoreDtoList, actualStoreDtoList);

        verify(storeRepository, times(1)).findAll();
    }

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