package com.migrosone.couriertracking.controller;

import com.migrosone.couriertracking.dto.StoreDto;
import com.migrosone.couriertracking.payload.response.GenericResponse;
import com.migrosone.couriertracking.payload.response.StoreListResponse;
import com.migrosone.couriertracking.service.contract.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller for Store operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@RestController
@RequestMapping("/v0/stores")
@RequiredArgsConstructor
public class ShopController {

    private final StoreService storeService;

    @GetMapping("")
    public ResponseEntity<GenericResponse<StoreListResponse>> getAllStores() {
        List<StoreDto> stores = storeService.getAllStores();
        GenericResponse<StoreListResponse> response = GenericResponse.success(new StoreListResponse(stores));
        return ResponseEntity.ok(response);
    }
}
