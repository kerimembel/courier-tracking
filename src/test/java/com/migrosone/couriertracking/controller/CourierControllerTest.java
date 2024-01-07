package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.payload.request.CourierLocationRequest;
import com.migrosone.couriertracking.service.contract.CourierService;
import com.migrosone.couriertracking.service.contract.CourierTrackingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = "COURIER")
public class CourierControllerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierTrackingService courierTrackingService;

    @MockBean
    private CourierService courierService;

    @Test
    public void testProcessCourierLocation() throws Exception {
        UUID courierId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        double lat = 40.9923307;
        double lng = 29.1244229;

        CourierLocationRequest request = new CourierLocationRequest(timestamp, lat, lng);

        doNothing().when(courierTrackingService).processCourierLocationUpdate(courierId, lat, lng, timestamp);
        mockMvc.perform(post("/v0/couriers/{courierId}/location", courierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Courier location processed successfully."));
    }

    @Test
    public void testGetTotalTravelDistance() throws Exception {
        UUID courierId = UUID.randomUUID();
        Double totalTravelDistance = 100.0;

        when(courierService.getTotalTravelDistance(courierId)).thenReturn(totalTravelDistance);

        mockMvc.perform(get("/v0/couriers/{courierId}/total-travel-distance", courierId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.totalTravelDistance").value(totalTravelDistance));
    }
}
