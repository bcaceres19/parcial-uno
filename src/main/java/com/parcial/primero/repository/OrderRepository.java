package com.parcial.primero.repository;

import com.parcial.primero.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing order data.
 * <p>
 * Provides CRUD operations for the "order" table.
 * </p>
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
