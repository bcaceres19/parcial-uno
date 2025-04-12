package com.parcial.primero.service;

import com.parcial.primero.dto.OrderDetailDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.OrderDto;
import com.parcial.primero.dto.ProductDto;
import com.parcial.primero.entity.OrderDetailEntity;
import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import com.parcial.primero.mapper.OrderDetailMapper;
import com.parcial.primero.repository.OrderDetailRepository;
import com.parcial.primero.service.impl.OrderDetailService;
import com.parcial.primero.util.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderDetailServiceTest {

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private OrderDetailMapper orderDetailMapper;

    @Spy
    private Utils utils;

    @InjectMocks
    private OrderDetailService orderDetailService;

    @Test
    public void testCreateOrderDetailSuccess() {
        // Configurar el DTO de entrada con objetos anidados con ID
        OrderDetailDto inputDto = new OrderDetailDto();

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        inputDto.setOrderFk(orderDto);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        inputDto.setProductFk(productDto);

        inputDto.setOrderDetailEmbeddedId(new OrderDetailEmbeddedId());

        OrderDetailEntity dummyEntity = new OrderDetailEntity();

        // Stub: Simular el mapeo y guardado
        when(orderDetailMapper.mapToEntity(inputDto)).thenReturn(dummyEntity);
        when(orderDetailRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(orderDetailMapper.mapToDto(dummyEntity)).thenReturn(inputDto);

        GeneralMessageDto result = orderDetailService.createOrderDetail(inputDto);

        assertFalse(result.isError(), "No se esperaba error en la creación.");
        assertEquals("Order detail successfully created.", result.getMessage());
        assertEquals(inputDto, result.getObject());
    }


    @Test
    public void testUpdateOrderDetailNotFound() {
        OrderDetailDto inputDto = new OrderDetailDto();

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        inputDto.setOrderFk(orderDto);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        inputDto.setProductFk(productDto);

        inputDto.setCode("ABC");

        OrderDetailEmbeddedId embeddedId = new OrderDetailEmbeddedId();
        embeddedId.setOrderFk(1L);
        embeddedId.setProductFk(1L);
        inputDto.setOrderDetailEmbeddedId(embeddedId);

        when(orderDetailRepository.findAllByOrderDetailEmbeddedId_productFkAndOrderDetailEmbeddedId_orderFkAndOrderDetailEmbeddedId_Code(
                eq(1L), eq(1L), eq("ABC")
        )).thenReturn(null);

        GeneralMessageDto result = orderDetailService.updateOrderDetail(inputDto);
        assertTrue(result.isError(), "Se esperaba error al no encontrar el detalle de orden.");
        assertEquals("Order detail not found.", result.getMessage());
    }

    @Test
    public void testDeleteOrderDetailSuccess() {
        OrderDetailEmbeddedId id = new OrderDetailEmbeddedId();
        id.setOrderFk(1L);
        id.setProductFk(1L);
        id.setCode("ABC");

        OrderDetailEntity dummyEntity = new OrderDetailEntity();
        OrderDetailDto existingDto = new OrderDetailDto();
        existingDto.setCode("ABC");
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        existingDto.setOrderFk(orderDto);
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        existingDto.setProductFk(productDto);

        when(orderDetailRepository.findAllByOrderDetailEmbeddedId_productFkAndOrderDetailEmbeddedId_orderFkAndOrderDetailEmbeddedId_Code(
                eq(1L), eq(1L), eq("ABC")
        )).thenReturn(dummyEntity);
        when(orderDetailMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        GeneralMessageDto result = orderDetailService.deleteOrderDetail(id);
        assertFalse(result.isError(), "No se esperaba error al eliminar el detalle existente.");
        assertEquals("Order detail successfully deleted.", result.getMessage());
    }

    @Test
    public void testDeleteOrderDetailNotFound() {
        OrderDetailEmbeddedId id = new OrderDetailEmbeddedId();
        id.setOrderFk(1L);
        id.setProductFk(1L);
        id.setCode("ABC");

        when(orderDetailRepository.findAllByOrderDetailEmbeddedId_productFkAndOrderDetailEmbeddedId_orderFkAndOrderDetailEmbeddedId_Code(
                eq(1L), eq(1L), eq("ABC")
        )).thenReturn(null);

        GeneralMessageDto result = orderDetailService.deleteOrderDetail(id);
        assertTrue(result.isError(), "Se esperaba error al no encontrar el detalle a eliminar.");
        assertEquals("Order detail not found.", result.getMessage());
    }

}
