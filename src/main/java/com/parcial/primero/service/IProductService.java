package com.parcial.primero.service;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.ProductDto;

/**
 * Service interface for managing products.
 * <p>
 * Provides methods for creating, updating, deleting, and retrieving products.
 * </p>
 */
public interface IProductService {

    /**
     * Creates a new product.
     *
     * @param productDto The product DTO containing product details.
     * @return A response message with status information.
     */
    GeneralMessageDto createProduct(ProductDto productDto);

    /**
     * Updates an existing product.
     *
     * @param productDto The product DTO with updated details.
     * @return A response message with status information.
     */
    GeneralMessageDto updateProduct(ProductDto productDto);

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to be deleted.
     * @return A response message with status information.
     */
    GeneralMessageDto deleteProduct(Long productId);

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return A response message containing the product details or an error message.
     */
    GeneralMessageDto getProduct(Long productId);
}
