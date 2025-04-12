package com.parcial.primero.service;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.OrderDto;
import com.parcial.primero.entity.OrderEntity;
import com.parcial.primero.mapper.OrderMapper;
import com.parcial.primero.repository.OrderRepository;
import com.parcial.primero.service.impl.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrderSuccess() {
        OrderDto inputDto = new OrderDto();
        inputDto.setId(1L);
        OrderEntity dummyEntity = new OrderEntity();

        when(orderMapper.mapToEntity(inputDto)).thenReturn(dummyEntity);
        when(orderRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(orderMapper.mapToDto(dummyEntity)).thenReturn(inputDto);

        GeneralMessageDto result = orderService.createOrder(inputDto);

        assertFalse(result.isError());
        assertEquals("Order successfully created.", result.getMessage());
        assertEquals(inputDto, result.getObject());
    }

    @Test
    public void testUpdateOrderSuccess() {
        OrderDto inputDto = new OrderDto();
        inputDto.setId(1L);
        OrderEntity dummyEntity = new OrderEntity();
        OrderDto existingDto = new OrderDto();
        existingDto.setId(1L);

        when(orderRepository.findById(inputDto.getId())).thenReturn(Optional.of(dummyEntity));
        when(orderMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        // Para efectos de test, asumimos que Utils.compareOldNewObject devuelve inputDto
        OrderDto comparedDto = inputDto;
        when(orderMapper.mapToEntity(comparedDto)).thenReturn(dummyEntity);
        when(orderRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(orderMapper.mapToDto(dummyEntity)).thenReturn(comparedDto);

        GeneralMessageDto result = orderService.updateOrder(inputDto);

        assertFalse(result.isError());
        assertEquals("Order successfully updated.", result.getMessage());
        assertEquals(comparedDto, result.getObject());
    }

    @Test
    public void testUpdateOrderNotFound() {
        OrderDto inputDto = new OrderDto();
        inputDto.setId(1L);
        when(orderRepository.findById(inputDto.getId())).thenReturn(Optional.empty());

        GeneralMessageDto result = orderService.updateOrder(inputDto);

        assertTrue(result.isError());
        assertEquals("Order not found.", result.getMessage());
        assertNull(result.getObject());
    }

    @Test
    public void testDeleteOrderSuccess() {
        Long orderId = 1L;
        OrderEntity dummyEntity = new OrderEntity();
        OrderDto existingDto = new OrderDto();
        existingDto.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(dummyEntity));
        when(orderMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        GeneralMessageDto result = orderService.deleteOrder(orderId);

        assertFalse(result.isError());
        assertEquals("Order successfully deleted.", result.getMessage());
    }

    @Test
    public void testDeleteOrderNotFound() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        GeneralMessageDto result = orderService.deleteOrder(orderId);

        assertTrue(result.isError());
        assertEquals("Order not found.", result.getMessage());
    }

    @Test
    public void testGetOrderSuccess() {
        Long orderId = 1L;
        OrderEntity dummyEntity = new OrderEntity();
        OrderDto existingDto = new OrderDto();
        existingDto.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(dummyEntity));
        when(orderMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        GeneralMessageDto result = orderService.getOrder(orderId);

        assertFalse(result.isError());
        assertEquals("Order retrieved successfully.", result.getMessage());
        assertEquals(existingDto, result.getObject());
    }
}
