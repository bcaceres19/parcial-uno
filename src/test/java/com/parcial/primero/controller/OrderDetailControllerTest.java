package com.parcial.primero.controller;

import com.parcial.primero.dto.OrderDetailDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import com.parcial.primero.service.IOrderDetailService;
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
public class OrderDetailControllerTest {

    @Mock
    private IOrderDetailService orderDetailService;

    @InjectMocks
    private OrderDetailController orderDetailController;

    @Test
    public void testCreateOrderDetailSuccess() {
        OrderDetailDto dto = new OrderDetailDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("OrderDetail created successfully");

        when(orderDetailService.createOrderDetail(any(OrderDetailDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderDetailController.createOrderDetail(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OrderDetail created successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testUpdateOrderDetailSuccess() {
        OrderDetailDto dto = new OrderDetailDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("OrderDetail updated successfully");

        when(orderDetailService.updateOrderDetail(any(OrderDetailDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderDetailController.updateOrderDetail(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OrderDetail updated successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testDeleteOrderDetailSuccess() {
        Long productId = 1L;
        Long orderId = 1L;
        String code = "ABC";
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("OrderDetail deleted successfully");

        when(orderDetailService.deleteOrderDetail(any(OrderDetailEmbeddedId.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderDetailController.deleteOrderDetail(productId, orderId, code);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OrderDetail deleted successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testGetOrderDetailSuccess() {
        Long productId = 1L;
        Long orderId = 1L;
        String code = "ABC";
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("OrderDetail retrieved successfully");

        when(orderDetailService.getOrderDetail(any(OrderDetailEmbeddedId.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = orderDetailController.getOrderDetail(productId, orderId, code);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OrderDetail retrieved successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }
}
