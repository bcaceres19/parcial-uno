package com.parcial.primero.repository;

import com.parcial.primero.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing product data.
 * <p>
 * Provides CRUD operations for the "product" table.
 * </p>
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
