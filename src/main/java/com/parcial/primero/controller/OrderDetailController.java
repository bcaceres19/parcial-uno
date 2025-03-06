package com.parcial.primero.controller;

import com.parcial.primero.dto.OrderDetailDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import com.parcial.primero.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that manages operations related to order details.
 * <p>
 * This controller provides endpoints to create, update, delete, and retrieve order details.
 * </p>
 */
@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    /**
     * Creates a new order detail.
     *
     * @param orderDetailDto The order detail data transfer object containing the details.
     * @return A response entity containing a general message with status information.
     */
    @PostMapping
    public ResponseEntity<GeneralMessageDto> createOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        GeneralMessageDto response = orderDetailService.createOrderDetail(orderDetailDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Updates an existing order detail.
     *
     * @param orderDetailDto The order detail data transfer object containing updated details.
     * @return A response entity containing a general message with status information.
     */
    @PutMapping
    public ResponseEntity<GeneralMessageDto> updateOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        GeneralMessageDto response = orderDetailService.updateOrderDetail(orderDetailDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Deletes an order detail using a composite ID.
     *
     * @param productId The ID of the associated product.
     * @param orderId   The ID of the associated order.
     * @return A response entity containing a general message with status information.
     */
    @DeleteMapping
    public ResponseEntity<GeneralMessageDto> deleteOrderDetail(
            @RequestParam(required = false) Long productId, @RequestParam(required = false) Long orderId) {
        OrderDetailEmbeddedId orderDetailId = new OrderDetailEmbeddedId();
        orderDetailId.setOrderFk(orderId);
        orderDetailId.setProductFk(productId);
        GeneralMessageDto response = orderDetailService.deleteOrderDetail(orderDetailId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Retrieves an order detail using a composite ID.
     *
     * @param productId The ID of the associated product.
     * @param orderId   The ID of the associated order.
     * @return A response entity containing the order detail or an error message.
     */
    @GetMapping
    public ResponseEntity<GeneralMessageDto> getOrderDetail(
            @RequestParam(required = false) Long productId, @RequestParam(required = false) Long orderId) {
        OrderDetailEmbeddedId orderDetailId = new OrderDetailEmbeddedId();
        orderDetailId.setOrderFk(orderId);
        orderDetailId.setProductFk(productId);
        GeneralMessageDto response = orderDetailService.getOrderDetail(orderDetailId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }
}
