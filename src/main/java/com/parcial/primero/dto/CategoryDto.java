package com.parcial.primero.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a category.
 * <p>
 * This DTO is used to transfer category-related data between the API and the service layer.
 * </p>
 *

 */
@Data
public class CategoryDto {

    /**
     * The unique identifier of the category.
     */
    private Long id;

    /**
     * The name of the category.
     */
    private String name;
}
