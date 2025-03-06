package com.parcial.primero.mapper;

import com.parcial.primero.dto.OrderDetailDto;
import com.parcial.primero.entity.OrderDetailEntity;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Mapper interface for converting between {@link OrderDetailEntity} and {@link OrderDetailDto}.
 */
@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetailDto mapToDto(OrderDetailEntity entity);

    OrderDetailEntity mapToEntity(OrderDetailDto dto);

    List<OrderDetailEntity> mapToEntity(List<OrderDetailDto> dto);

    List<OrderDetailDto> mapToDto(List<OrderDetailEntity> entity);
}
