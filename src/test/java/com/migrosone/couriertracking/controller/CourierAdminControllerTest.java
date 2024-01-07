package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.service.contract.CourierService;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WithMockUser(username = "testadmin", authorities = "ADMIN")
public class CourierAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierService courierService;

    @Test
    public void testGetAllCouriers() throws Exception {
        Courier courier1 = new Courier();
        Courier courier2 = new Courier();
        courier1.setId(UUID.randomUUID());
        courier2.setId(UUID.randomUUID());

        List<Courier> couriers = Arrays.asList(courier1, courier2);
        when(courierService.getAllCouriers()).thenReturn(couriers);

        ResultActions resultActions = mockMvc.perform(get("/v0/admin/couriers"));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(courier1.getId().toString()))
                .andExpect(jsonPath("$.data[1].id").value(courier2.getId().toString()));
    }

    @Test
    @WithMockUser(username = "testuser", authorities = "COURIER")
    public void testGetAllCouriers_Unauthorized() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/v0/admin/couriers"));
        resultActions.andExpect(status().isForbidden());
    }

    @Test
    public void testGetCourierById() throws Exception {
        UUID courierId = UUID.randomUUID();
        Courier courier = new Courier();
        courier.setId(courierId);
        when(courierService.getCourierById(courierId)).thenReturn(courier);

        ResultActions resultActions = mockMvc.perform(get("/v0/admin/couriers/{courierId}", courierId));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(courierId.toString()));
    }

    @Test
    public void testDeleteCourier() throws Exception {
        UUID courierId = UUID.randomUUID();

        ResultActions resultActions = mockMvc.perform(delete("/v0/admin/couriers/{courierId}", courierId));

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value("Courier deleted successfully."));
    }
}