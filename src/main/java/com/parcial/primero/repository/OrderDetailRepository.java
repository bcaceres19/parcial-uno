package com.parcial.primero.repository;

import com.parcial.primero.entity.OrderDetailEntity;
import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for accessing order detail data.
 * <p>
 * Provides CRUD operations for the "order_detail" table.
 * </p>
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, OrderDetailEmbeddedId> {

    OrderDetailEntity findAllByOrderDetailEmbeddedId_productFkAndOrderDetailEmbeddedId_orderFkAndOrderDetailEmbeddedId_Code(Long productId, Long orderId, String codigo);

    /**
     * Native SQL Query to update an Order Detail
     *
     * @param orderFk The ID of the order
     * @param productFk The ID of the product
     * @param quantity The new quantity value
     * @param unitPrice The new unit price value
     * @param code The unique code of the order detail
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE order_detail " +
            "SET quantity = :quantity, unit_price = :unitPrice " +
            "WHERE order_fk = :orderFk AND product_fk = :productFk AND code_order_detail = :code",
            nativeQuery = true)
    void updateOrderDetail(Long orderFk, Long productFk, Integer quantity, BigDecimal unitPrice, String code);

}
