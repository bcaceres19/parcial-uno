package com.parcial.primero.entity.embeddedid;

import com.parcial.primero.util.Utils;
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

    /**
     * Code of the order detail
     */
    @Column(name = "code_order_detail")
    private String code = Utils.generateRandomCode(10);

}
