package com.migrosone.couriertracking.util.impl;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.util.StoreEntryStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link StoreEntryStrategy}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class EnterStoreObserverTest {

    @Mock
    private StoreEntryStrategy storeEntryStrategy;

    @InjectMocks
    private EnterStoreObserver enterStoreObserver;

    @Test
    public void testHandleLocationUpdate() {
        Courier courier = new Courier();
        double latitude = 40.7128;
        double longitude = -74.0060;
        LocalDateTime timestamp = LocalDateTime.now();

        enterStoreObserver.handleLocationUpdate(courier, latitude, longitude, timestamp);

        verify(storeEntryStrategy).checkStoreEntry(courier, latitude, longitude, timestamp);
    }
}
