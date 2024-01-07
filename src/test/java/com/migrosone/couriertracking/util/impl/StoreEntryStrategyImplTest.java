package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.CourierEntry;
import com.migrosone.couriertracking.model.Store;
import com.migrosone.couriertracking.repository.CourierEntryRepository;
import com.migrosone.couriertracking.service.contract.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link StoreEntryStrategyImpl}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class StoreEntryStrategyImplTest {

    @Mock
    private StoreService storeService;

    @Mock
    private CourierEntryRepository courierEntryRepository;

    @InjectMocks
    private StoreEntryStrategyImpl storeEntryStrategy;

    @Test
    public void testHandleStoreEntry() {
        Courier courier = new Courier();
        double latitude = 40.9923307;
        double longitude = 29.1244229;
        LocalDateTime timestamp = LocalDateTime.now();
        Store store1 = new Store();
        store1.setId(UUID.randomUUID());
        Store store2 = new Store();
        store2.setId(UUID.randomUUID());

        when(storeService.findStoresWithinDistance(latitude, longitude, 100.0))
                .thenReturn(Arrays.asList(store1, store2));
        when(courierEntryRepository.existsByCourierAndStoreAndEntryTimeAfter(courier, store1,
                timestamp.minusMinutes(1)))
                .thenReturn(false);
        when(courierEntryRepository.existsByCourierAndStoreAndEntryTimeAfter(courier, store2,
                timestamp.minusMinutes(1)))
                .thenReturn(true);

        storeEntryStrategy.checkStoreEntry(courier, latitude, longitude, timestamp);

        verify(courierEntryRepository, times(1)).save(any(CourierEntry.class));
    }
}