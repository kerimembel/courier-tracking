package com.migrosone.couriertracking.mapper;

import com.migrosone.couriertracking.dto.StoreDto;
import com.migrosone.couriertracking.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper for {@link Store}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    StoreDto toDto(Store store);

    List<StoreDto> toDto(List<Store> stores);
}
