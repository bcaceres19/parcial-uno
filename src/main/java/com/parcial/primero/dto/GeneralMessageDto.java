package com.parcial.primero.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing a general message.
 * <p>
 * This DTO is used to return general messages and response objects from the API.
 * </p>
 */
@Data
public class GeneralMessageDto {

    /**
     * The response message.
     */
    private String message;

    /**
     * The object associated with the response.
     */
    private Object object;

    /**
     * Indicates whether an error occurred.
     */
    private boolean error;
}
