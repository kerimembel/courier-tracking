package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.dto.CourierEntryDto;
import com.migrosone.couriertracking.service.contract.CourierEntryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for {@link CourierEntryController}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WithMockUser(username = "testcourier", authorities = "COURIER")
public class CourierEntryControllerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CourierEntryService courierEntryService;

    @Test
    public void testGetAllStoreEntriesForCourier() throws Exception {
        UUID courierId = UUID.randomUUID();

        List<CourierEntryDto> entries = Collections.singletonList(new CourierEntryDto());
        when(courierEntryService.getAllStoreEntriesForCourier(courierId)).thenReturn(entries);

        mockMvc.perform(get("/v0/couriers/{courierId}/entries", courierId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.courierEntries").isArray());
    }

    @Test
    public void testGetStoreEntryForCourier() throws Exception {
        UUID courierId = UUID.randomUUID();
        UUID storeId = UUID.randomUUID();

        List<CourierEntryDto> storeEntries = Collections.singletonList(new CourierEntryDto());
        when(courierEntryService.getStoreEntryForCourier(courierId, storeId)).thenReturn(storeEntries);

        mockMvc.perform(get("/v0/couriers/{courierId}/entries/stores/{storeId}", courierId, storeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.courierEntries").isArray());
    }
}
