package com.parcial.primero.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a client.
 * <p>
 * This DTO is used to transfer client-related data between the API and the service layer.
 * </p>
 *
 */
@Data
public class ClientDto {

    /**
     * The unique identifier of the client.
     */
    private Long id;

    /**
     * The name of the client.
     */
    private String name;

    /**
     * The email address of the client.
     */
    private String email;

    /**
     * The phone number of the client.
     */
    private String phone;

    /**
     * The physical address of the client.
     */
    private String address;
}
