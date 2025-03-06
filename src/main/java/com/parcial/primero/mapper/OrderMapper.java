package com.parcial.primero.mapper;

import com.parcial.primero.dto.OrderDto;
import com.parcial.primero.entity.OrderEntity;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Mapper interface for converting between {@link OrderEntity} and {@link OrderDto}.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto mapToDto(OrderEntity entity);

    OrderEntity mapToEntity(OrderDto dto);

    List<OrderEntity> mapToEntity(List<OrderDto> dto);

    List<OrderDto> mapToDto(List<OrderEntity> entity);
}
