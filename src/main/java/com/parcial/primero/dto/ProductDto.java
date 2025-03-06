package com.parcial.primero.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) representing a product.
 * <p>
 * This DTO is used to transfer product-related data between the API and the service layer.
 * </p>
 */
@Data
public class ProductDto {

    /**
     * The unique identifier of the product.
     */
    private Long id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     */
    private BigDecimal price;

    /**
     * The stock quantity available for the product.
     */
    private Integer stock;

    /**
     * The category to which the product belongs.
     */
    private CategoryDto category;
}
