package com.parcial.primero.mapper;

import com.parcial.primero.dto.ProductDto;
import com.parcial.primero.entity.ProductEntity;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Mapper interface for converting between {@link ProductEntity} and {@link ProductDto}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapToDto(ProductEntity entity);

    ProductEntity mapToEntity(ProductDto dto);

    List<ProductEntity> mapToEntity(List<ProductDto> dto);

    List<ProductDto> mapToDto(List<ProductEntity> entity);
}
