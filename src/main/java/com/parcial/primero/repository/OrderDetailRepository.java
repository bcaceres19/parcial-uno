package com.parcial.primero.repository;

import com.parcial.primero.entity.OrderDetailEntity;
import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing order detail data.
 * <p>
 * Provides CRUD operations for the "order_detail" table.
 * </p>
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, OrderDetailEmbeddedId> {
}
