package com.parcial.primero.mapper;

import com.parcial.primero.dto.CategoryDto;
import com.parcial.primero.entity.CategoryEntity;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Mapper interface for converting between {@link CategoryEntity} and {@link CategoryDto}.
 * Utilizes MapStruct for automatic mapping.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    /**
     * Converts a {@link CategoryEntity} to a {@link CategoryDto}.
     */
    CategoryDto mapToDto(CategoryEntity entity);

    /**
     * Converts a {@link CategoryDto} to a {@link CategoryEntity}.
     */
    CategoryEntity mapToEntity(CategoryDto dto);

    /**
     * Converts a list of {@link CategoryDto} to a list of {@link CategoryEntity}.
     */
    List<CategoryEntity> mapToEntity(List<CategoryDto> dto);

    /**
     * Converts a list of {@link CategoryEntity} to a list of {@link CategoryDto}.
     */
    List<CategoryDto> mapToDto(List<CategoryEntity> entity);
}
