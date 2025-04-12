package com.parcial.primero.controller;

import com.parcial.primero.dto.OrderDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private IOrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void testCreateOrderSuccess() {
        OrderDto orderDto = new OrderDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Order created successfully");

        when(orderService.createOrder(any(OrderDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderController.createOrder(orderDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order created successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testUpdateOrderSuccess() {
        OrderDto orderDto = new OrderDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Order updated successfully");

        when(orderService.updateOrder(any(OrderDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderController.updateOrder(orderDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order updated successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testDeleteOrderSuccess() {
        Long orderId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Order deleted successfully");

        when(orderService.deleteOrder(orderId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderController.deleteOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testGetOrderSuccess() {
        Long orderId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Order retrieved successfully");

        when(orderService.getOrder(orderId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderController.getOrder(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order retrieved successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }
}
