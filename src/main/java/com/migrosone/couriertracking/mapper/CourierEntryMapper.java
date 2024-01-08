package com.migrosone.couriertracking.mapper;

import com.migrosone.couriertracking.dto.CourierEntryDto;
import com.migrosone.couriertracking.model.CourierEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper for {@link CourierEntry}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Mapper
public interface CourierEntryMapper {

    CourierEntryMapper INSTANCE = Mappers.getMapper(CourierEntryMapper.class);

    @Mapping(target = "storeId", source = "store.id")
    @Mapping(target = "courierId", source = "courier.id")
    CourierEntryDto toDto(CourierEntry courierEntry);

    List<CourierEntryDto> toDto(List<CourierEntry> courierEntries);
}
