package com.parcial.primero.repository;

import com.parcial.primero.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing category data.
 * <p>
 * Provides CRUD operations for the "category" table.
 * </p>
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
