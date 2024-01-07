package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.model.Courier;
import com.migrosone.couriertracking.payload.response.GenericResponse;
import com.migrosone.couriertracking.service.contract.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Rest Controller for Courier operations for Admin.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RestController
@RequestMapping("/v0/admin/couriers")
@RequiredArgsConstructor
public class CourierAdminController {

    private final CourierService courierService;

    @GetMapping()
    public ResponseEntity<GenericResponse<List<Courier>>> getAllCouriers() {
        List<Courier> couriers = courierService.getAllCouriers();
        GenericResponse<List<Courier>> response = GenericResponse.success(couriers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{courierId}")
    public ResponseEntity<GenericResponse<Courier>> getCourierById(@PathVariable UUID courierId) {
        Courier courier = courierService.getCourierById(courierId);
        GenericResponse<Courier> response = GenericResponse.success(courier);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{courierId}")
    public ResponseEntity<GenericResponse<String>> deleteCourier(@PathVariable UUID courierId) {
        courierService.deleteCourier(courierId);
        GenericResponse<String> response = GenericResponse.success("Courier deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
