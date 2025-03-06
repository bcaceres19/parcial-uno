package com.parcial.primero.service.impl;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.OrderDto;
import com.parcial.primero.mapper.OrderMapper;
import com.parcial.primero.repository.OrderRepository;
import com.parcial.primero.service.IOrderService;
import com.parcial.primero.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing orders.
 * <p>
 * This class handles order creation, updating, deletion, and retrieval.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public GeneralMessageDto createOrder(OrderDto orderDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            message.setMessage("Order successfully created.");
            message.setObject(orderMapper.mapToDto(orderRepository.save(orderMapper.mapToEntity(orderDto))));
        } catch (Exception ex) {
            log.error("Error while saving order: {}", ex.getMessage());
            message.setMessage("Error saving the order.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto updateOrder(OrderDto orderDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            OrderDto existingOrder = orderMapper.mapToDto(orderRepository.findById(orderDto.getId()).orElse(null));
            if (existingOrder != null) {
                message.setMessage("Order successfully updated.");
                OrderDto updatedOrder = Utils.compareOldNewObject(existingOrder, orderDto);
                message.setObject(orderMapper.mapToDto(orderRepository.save(orderMapper.mapToEntity(updatedOrder))));
            } else {
                message.setMessage("Order not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error updating order: {}", ex.getMessage());
            message.setMessage("Error updating the order.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto deleteOrder(Long orderId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            OrderDto orderDto = orderMapper.mapToDto(orderRepository.findById(orderId).orElse(null));
            if (orderDto != null) {
                message.setMessage("Order successfully deleted.");
                orderRepository.delete(orderMapper.mapToEntity(orderDto));
            } else {
                message.setMessage("Order not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error deleting order: {}", ex.getMessage());
            message.setMessage("Error deleting the order.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto getOrder(Long orderId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            OrderDto orderDto = orderMapper.mapToDto(orderRepository.findById(orderId).orElse(null));
            message.setMessage("Order retrieved successfully.");
            message.setObject(orderDto);
        } catch (Exception ex) {
            log.error("Error retrieving order: {}", ex.getMessage());
            message.setMessage("Error retrieving the order.");
            message.setError(true);
        }
        return message;
    }
}
