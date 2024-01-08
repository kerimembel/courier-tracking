package com.migrosone.couriertracking.mapper;

import com.migrosone.couriertracking.dto.CourierDto;
import com.migrosone.couriertracking.model.Courier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper for {@link Courier}.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Mapper
public interface CourierMapper {

    CourierMapper INSTANCE = Mappers.getMapper(CourierMapper.class);

    @Mapping(target = "userId", source = "user.id")
    CourierDto toDto(Courier courier);

    List<CourierDto> toDto(List<Courier> couriers);
}
