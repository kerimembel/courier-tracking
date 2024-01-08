package com.migrosone.couriertracking.service.impl;

import com.migrosone.couriertracking.dto.CourierEntryDto;
import com.migrosone.couriertracking.mapper.CourierEntryMapper;
import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.model.CourierEntry;
import com.migrosone.couriertracking.model.Store;
import com.migrosone.couriertracking.repository.CourierEntryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link CourierEntryServiceImpl}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
public class CourierEntryServiceImplTest {

    private final CourierEntryMapper courierEntryMapper = CourierEntryMapper.INSTANCE;

    @Mock
    private CourierEntryRepository courierEntryRepository;

    @InjectMocks
    private CourierEntryServiceImpl courierEntryService;

    @Test
    public void getAllStoreEntriesForCourier() {
        UUID courierId = UUID.randomUUID();
        List<CourierEntry> mockEntries = createMockEntries(courierId, createMockStores());

        when(courierEntryRepository.findAllByCourierId(Mockito.eq(courierId))).thenReturn(mockEntries);

        List<CourierEntryDto> result = courierEntryService.getAllStoreEntriesForCourier(courierId);

        assertEquals(courierEntryMapper.toDto(mockEntries), result);
    }

    @Test
    public void getStoreEntryForCourier() {
        UUID courierId = UUID.randomUUID();
        UUID storeId = UUID.randomUUID();
        List<CourierEntry> mockEntries = createMockEntries(courierId, createMockStores());

        when(courierEntryRepository.findAllByCourierIdAndStoreId(Mockito.eq(courierId), Mockito.eq(storeId))).thenReturn(mockEntries);

        List<CourierEntryDto> result = courierEntryService.getStoreEntryForCourier(courierId, storeId);

        assertEquals(courierEntryMapper.toDto(mockEntries), result);
    }

    private List<Store> createMockStores() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Store());
        stores.add(new Store());
        return stores;
    }

    private List<CourierEntry> createMockEntries(UUID courierId, List<Store> stores) {
        List<CourierEntry> mockEntries = new ArrayList<>();
        for (Store store : stores) {
            mockEntries.add(createCourierEntry(courierId, store, LocalDateTime.now()));
        }
        return mockEntries;
    }

    private CourierEntry createCourierEntry(UUID courierId, Store store, LocalDateTime entryTime) {
        Courier courier = new Courier();
        courier.setId(courierId);
        CourierEntry courierEntry = new CourierEntry();
        courierEntry.setId(UUID.randomUUID());
        courierEntry.setCourier(courier);
        courierEntry.setStore(store);
        courierEntry.setEntryTime(entryTime);
        return courierEntry;
    }
}