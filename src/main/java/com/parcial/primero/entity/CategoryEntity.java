package com.parcial.primero.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing a category.
 * <p>
 * This entity maps to the "category" table in the database.
 * </p>
 */
@Entity
@Table(name = "category")
@Data
public class CategoryEntity {

    /**
     * The unique identifier of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category.
     */
    @Column(nullable = false, length = 100, unique = true)
    private String name;
}
