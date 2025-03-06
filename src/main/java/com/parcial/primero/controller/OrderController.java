package com.parcial.primero.controller;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.OrderDto;
import com.parcial.primero.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that manages operations related to orders.
 * <p>
 * This controller provides endpoints to create, update, delete, and retrieve orders.
 * </p>
 *
 * @author YourName
 * @version 1.0
 * @since 2025-03-05
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    /**
     * Creates a new order.
     *
     * @param orderDto The order data transfer object containing order details.
     * @return A response entity containing a general message with status information.
     */
    @PostMapping
    public ResponseEntity<GeneralMessageDto> createOrder(@RequestBody OrderDto orderDto) {
        GeneralMessageDto response = orderService.createOrder(orderDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Updates an existing order.
     *
     * @param orderDto The order data transfer object containing updated details.
     * @return A response entity containing a general message with status information.
     */
    @PutMapping
    public ResponseEntity<GeneralMessageDto> updateOrder(@RequestBody OrderDto orderDto) {
        GeneralMessageDto response = orderService.updateOrder(orderDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId The ID of the order to be deleted.
     * @return A response entity containing a general message with status information.
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<GeneralMessageDto> deleteOrder(@PathVariable Long orderId) {
        GeneralMessageDto response = orderService.deleteOrder(orderId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return A response entity containing the order details or an error message.
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<GeneralMessageDto> getOrder(@PathVariable Long orderId) {
        GeneralMessageDto response = orderService.getOrder(orderId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }
}
