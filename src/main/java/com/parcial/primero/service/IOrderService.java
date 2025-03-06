package com.parcial.primero.service;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.OrderDto;

/**
 * Service interface for managing orders.
 * <p>
 * Provides methods for creating, updating, deleting, and retrieving orders.
 * </p>
 */
public interface IOrderService {

    /**
     * Creates a new order.
     *
     * @param orderDto The order DTO containing order details.
     * @return A response message with status information.
     */
    GeneralMessageDto createOrder(OrderDto orderDto);

    /**
     * Updates an existing order.
     *
     * @param orderDto The order DTO with updated details.
     * @return A response message with status information.
     */
    GeneralMessageDto updateOrder(OrderDto orderDto);

    /**
     * Deletes an order by its ID.
     *
     * @param orderId The ID of the order to be deleted.
     * @return A response message with status information.
     */
    GeneralMessageDto deleteOrder(Long orderId);

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return A response message containing the order details or an error message.
     */
    GeneralMessageDto getOrder(Long orderId);
}
