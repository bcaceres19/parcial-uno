package com.parcial.primero.service;

import com.parcial.primero.dto.OrderDetailDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;

/**
 * Service interface for managing order details.
 * <p>
 * Provides methods for creating, updating, deleting, and retrieving order details.
 * </p>
 */
public interface IOrderDetailService {

    /**
     * Creates a new order detail.
     *
     * @param orderDetailDto The order detail DTO containing the details.
     * @return A response message with status information.
     */
    GeneralMessageDto createOrderDetail(OrderDetailDto orderDetailDto);

    /**
     * Updates an existing order detail.
     *
     * @param orderDetailDto The order detail DTO with updated details.
     * @return A response message with status information.
     */
    GeneralMessageDto updateOrderDetail(OrderDetailDto orderDetailDto);

    /**
     * Deletes an order detail using a composite ID.
     *
     * @param orderDetailId The composite ID of the order detail.
     * @return A response message with status information.
     */
    GeneralMessageDto deleteOrderDetail(OrderDetailEmbeddedId orderDetailId);

    /**
     * Retrieves an order detail using a composite ID.
     *
     * @param orderDetailId The composite ID of the order detail.
     * @return A response message containing the order detail or an error message.
     */
    GeneralMessageDto getOrderDetail(OrderDetailEmbeddedId orderDetailId);
}
