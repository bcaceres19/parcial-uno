package com.parcial.primero.service.impl;

import com.parcial.primero.dto.OrderDetailDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import com.parcial.primero.mapper.OrderDetailMapper;
import com.parcial.primero.repository.OrderDetailRepository;
import com.parcial.primero.service.IOrderDetailService;
import com.parcial.primero.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Service implementation for managing order details.
 * <p>
 * This class handles order detail creation, updating, deletion, and retrieval.
 * </p>
 *
 * @author YourName
 * @version 1.0
 * @since 2025-03-05
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class OrderDetailService implements IOrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    public GeneralMessageDto createOrderDetail(OrderDetailDto orderDetailDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        OrderDetailEmbeddedId orderDetailEmbeddedId = new OrderDetailEmbeddedId();
        try {
            message.setMessage("Order detail successfully created.");
            orderDetailEmbeddedId.setOrderFk(orderDetailDto.getOrderFk().getId());
            orderDetailEmbeddedId.setProductFk(orderDetailDto.getProductFk().getId());
            orderDetailDto.setOrderDetailEmbeddedId(orderDetailEmbeddedId);
            message.setObject(orderDetailMapper.mapToDto(orderDetailRepository.save(orderDetailMapper.mapToEntity(orderDetailDto))));
        } catch (Exception ex) {
            log.error("Error while saving order detail: {}", ex.getMessage());
            message.setMessage("Error saving the order detail.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto updateOrderDetail(OrderDetailDto orderDetailDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            OrderDetailDto existingOrderDetail = orderDetailMapper.mapToDto(orderDetailRepository.findById(orderDetailDto.getOrderDetailEmbeddedId()).orElse(null));
            if (existingOrderDetail != null) {
                message.setMessage("Order detail successfully updated.");
                OrderDetailDto updatedOrderDetail = Utils.compareOldNewObject(existingOrderDetail, orderDetailDto);
                message.setObject(orderDetailMapper.mapToDto(orderDetailRepository.save(orderDetailMapper.mapToEntity(updatedOrderDetail))));
            } else {
                message.setMessage("Order detail not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error updating order detail: {}", ex.getMessage());
            message.setMessage("Error updating the order detail.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto deleteOrderDetail(OrderDetailEmbeddedId orderDetailId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            OrderDetailDto orderDetailDto = orderDetailMapper.mapToDto(orderDetailRepository.findById(orderDetailId).orElse(null));
            if (orderDetailDto != null) {
                message.setMessage("Order detail successfully deleted.");
                orderDetailRepository.delete(orderDetailMapper.mapToEntity(orderDetailDto));
            } else {
                message.setMessage("Order detail not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error deleting order detail: {}", ex.getMessage());
            message.setMessage("Error deleting the order detail.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto getOrderDetail(OrderDetailEmbeddedId orderDetailId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            List<OrderDetailDto> orderDetailDto = orderDetailMapper.mapToDto(Arrays.asList(orderDetailRepository.findById(orderDetailId).orElse(null)));
            message.setMessage("Order detail retrieved successfully.");
            message.setObject(orderDetailDto);
        } catch (Exception ex) {
            log.error("Error retrieving order detail: {}", ex.getMessage());
            message.setMessage("Error retrieving the order detail.");
            message.setError(true);
        }
        return message;
    }
}
