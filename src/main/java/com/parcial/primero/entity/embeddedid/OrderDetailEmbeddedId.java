package com.parcial.primero.entity.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Embedded ID for Order Detail entity.
 * <p>
 * This class represents the composite primary key for the "order_detail" table.
 * </p>
 *
 * @author YourName
 * @version 1.0
 * @since 2025-03-05
 */
@Embeddable
@Data
public class OrderDetailEmbeddedId {

    /**
     * Foreign key referencing the product.
     */
    @Column
    private Long productFk;

    /**
     * Foreign key referencing the order.
     */
    @Column
    private Long orderFk;

    /**
     * Registration timestamp.
     */
    @Column(name = "registration_date")
    private LocalDateTime registrationDate = LocalDateTime.now();
}
