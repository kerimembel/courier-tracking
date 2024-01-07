package com.migrosone.couriertracking.repository;

import com.migrosone.couriertracking.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for {@link Store} entity.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

    @Query(value = "SELECT * FROM tbl_store s " +
            "WHERE ST_DWithin(cast(ST_MakePoint(:longitude, :latitude) as geography), " +
            "cast(s.location as geography), :maxDistance)", nativeQuery = true)
    List<Store> findStoresWithinDistance(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("maxDistance") double maxDistance);

}
