package com.parcial.primero.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing an order.
 * <p>
 * This DTO is used to transfer order-related data between the API and the service layer.
 * </p>
 */
@Data
public class OrderDto {

    /**
     * The unique identifier of the order.
     */
    private Long id;

    /**
     * The client associated with the order.
     */
    private ClientDto client;

    /**
     * The date and time when the order was placed.
     */
    private LocalDateTime orderDate;

    /**
     * The status of the order (e.g., Pending, Shipped, Delivered, Canceled).
     */
    private String status;
}
