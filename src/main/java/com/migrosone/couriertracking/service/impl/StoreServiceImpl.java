package com.migrosone.couriertracking.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migrosone.couriertracking.dto.StoreDto;
import com.migrosone.couriertracking.mapper.StoreMapper;
import com.migrosone.couriertracking.model.Store;
import com.migrosone.couriertracking.repository.StoreRepository;
import com.migrosone.couriertracking.service.contract.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of {@link StoreService}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final GeometryFactory geometryFactory;
    private final StoreMapper storeMapper = StoreMapper.INSTANCE;

    @Override
    public List<Store> findStoresWithinDistance(double latitude, double longitude, double maxDistance) {
        return storeRepository.findStoresWithinDistance(latitude, longitude, maxDistance);
    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeMapper.toDto(storeRepository.findAll());
    }

    @Override
    public void loadStoreLocationsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ClassPathResource resource = new ClassPathResource("stores.json");
            List<Map<String, Object>> storeMaps = objectMapper.readValue(resource.getInputStream(),
                    new TypeReference<>() {
                    });

            List<Store> stores = storeMaps.stream()
                    .map(storeMap -> {
                        String name = (String) storeMap.get("name");
                        Double latitude = (Double) storeMap.get("lat");
                        Double longitude = (Double) storeMap.get("lng");

                        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

                        return new Store(name, point);
                    })
                    .collect(Collectors.toList());

            storeRepository.saveAll(stores);

            log.info("Store locations have been successfully loaded.");
        } catch (IOException e) {
            log.error("An error occurred while loading store locations.");
        } catch (DataIntegrityViolationException e) {
            log.info("Stores already exists.");
        }
    }
}
