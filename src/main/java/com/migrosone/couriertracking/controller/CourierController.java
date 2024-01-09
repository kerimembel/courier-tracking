package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.dto.TotalDistanceDto;
import com.migrosone.couriertracking.payload.request.CourierLocationRequest;
import com.migrosone.couriertracking.payload.response.GenericResponse;
import com.migrosone.couriertracking.payload.response.TotalDistanceResponse;
import com.migrosone.couriertracking.service.contract.CourierService;
import com.migrosone.couriertracking.service.contract.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Rest Controller for Courier operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RestController
@RequestMapping("/v0/couriers/{courierId}")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;
    private final CourierTrackingService courierTrackingService;

    @PostMapping("/location")
    public ResponseEntity<GenericResponse<String>> processCourierLocation(
            @PathVariable UUID courierId,
            @RequestBody CourierLocationRequest request
    ) {
        courierTrackingService.processCourierLocationUpdate(
                courierId,
                request.getLatitude(),
                request.getLongitude(),
                request.getTimestamp()
        );
        GenericResponse<String> response = GenericResponse.success("Courier location processed successfully.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-travel-distance")
    public ResponseEntity<GenericResponse<TotalDistanceResponse>> getTotalTravelDistance(@PathVariable UUID courierId) {
        TotalDistanceDto totalTravelDistance = courierService.getTotalTravelDistance(courierId);
        GenericResponse<TotalDistanceResponse> response =
                GenericResponse.success(new TotalDistanceResponse(totalTravelDistance));
        return ResponseEntity.ok(response);
    }
}
