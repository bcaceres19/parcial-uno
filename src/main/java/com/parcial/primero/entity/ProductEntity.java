package com.parcial.primero.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Entity representing a product.
 * <p>
 * This entity maps to the "product" table in the database.
 * </p>
 *
 */
@Entity
@Table(name = "product")
@Data
public class ProductEntity {

    /**
     * The unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * The price of the product.
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * The stock quantity available for the product.
     */
    @Column(nullable = false)
    private Integer stock;

    /**
     * The category to which the product belongs.
     */
    @ManyToOne
    @JoinColumn(name = "category_fk", nullable = false)
    private CategoryEntity category;
}
