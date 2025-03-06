package com.parcial.primero.entity;

import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Entity representing an order detail.
 * <p>
 * This entity maps to the "order_detail" table in the database.
 * </p>
 */
@Entity
@Table(name = "order_detail")
@Data
public class OrderDetailEntity {

    /**
     * The composite key for the order detail.
     */
    @EmbeddedId
    private OrderDetailEmbeddedId orderDetailEmbeddedId;

    /**
     * The associated order.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderFk")
    @JoinColumn(name = "order_fk", nullable = false)
    private OrderEntity orderFk;

    /**
     * The associated product.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productFk")
    @JoinColumn(name = "product_fk", nullable = false)
    private ProductEntity productFk;

    /**
     * The quantity of the product in the order.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * The unit price of the product in the order.
     */
    @Column(nullable = false)
    private BigDecimal unitPrice;
}
