package com.parcial.primero.dto;

import com.parcial.primero.entity.embeddedid.OrderDetailEmbeddedId;
import lombok.Data;
import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) representing an order detail.
 * <p>
 * This DTO is used to transfer order detail-related data between the API and the service layer.
 * </p>
 */
@Data
public class OrderDetailDto {

    /**
     * The composite key of the order detail, including the order ID and product ID.
     */
    private OrderDetailEmbeddedId orderDetailEmbeddedId;

    /**
     * The associated order.
     */
    private OrderDto orderFk;

    /**
     * The associated product.
     */
    private ProductDto productFk;

    /**
     * The quantity of the product in the order.
     */
    private Integer quantity;

    /**
     * The unit price of the product in the order.
     */
    private BigDecimal unitPrice;

    private String code;

}
