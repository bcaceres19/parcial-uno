package com.parcial.primero.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entity representing an orders.
 * <p>
 * This entity maps to the "order" table in the database.
 * </p>
 *
 */
@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    /**
     * The unique identifier of the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The client associated with the order.
     */
    @ManyToOne
    @JoinColumn(name = "client_fk", nullable = false)
    private ClientEntity client;

    /**
     * The date and time when the order was placed.
     */
    @Column(nullable = false)
    private LocalDateTime orderDate;

    /**
     * The status of the order (Pending, Shipped, Delivered, Canceled).
     */
    @Column(nullable = false)
    private String status;
}
