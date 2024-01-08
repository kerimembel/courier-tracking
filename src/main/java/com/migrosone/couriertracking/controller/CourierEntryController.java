package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.dto.CourierEntryDto;
import com.migrosone.couriertracking.payload.response.CourierEntryListResponse;
import com.migrosone.couriertracking.payload.response.GenericResponse;
import com.migrosone.couriertracking.service.contract.CourierEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Rest Controller for Courier Entry operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RestController
@RequestMapping("/v0/couriers/{courierId}/entries")
@RequiredArgsConstructor
public class CourierEntryController {

    private final CourierEntryService courierEntryService;

    @GetMapping("")
    public ResponseEntity<GenericResponse<CourierEntryListResponse>> getAllStoreEntriesForCourier(@PathVariable UUID courierId) {
        List<CourierEntryDto> entries = courierEntryService.getAllStoreEntriesForCourier(courierId);

        GenericResponse<CourierEntryListResponse> response =
                GenericResponse.success(new CourierEntryListResponse(entries));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<GenericResponse<CourierEntryListResponse>> getStoreEntryForCourier(
            @PathVariable UUID courierId,
            @PathVariable UUID storeId
    ) {
        List<CourierEntryDto> storeEntries = courierEntryService.getStoreEntryForCourier(courierId, storeId);

        GenericResponse<CourierEntryListResponse> response =
                GenericResponse.success(new CourierEntryListResponse(storeEntries));
        return ResponseEntity.ok(response);
    }
}
