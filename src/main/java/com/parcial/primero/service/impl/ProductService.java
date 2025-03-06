package com.parcial.primero.service.impl;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.ProductDto;
import com.parcial.primero.mapper.ProductMapper;
import com.parcial.primero.repository.ProductRepository;
import com.parcial.primero.service.IProductService;
import com.parcial.primero.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing products.
 * <p>
 * This class handles product creation, updating, deletion, and retrieval.
 * </p>
 *
 * @author YourName
 * @version 1.0
 * @since 2025-03-05
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Creates a new product.
     *
     * @param productDto the product details
     * @return a {@link GeneralMessageDto} containing the result of the operation
     */
    @Override
    public GeneralMessageDto createProduct(ProductDto productDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            message.setMessage("Product successfully created.");
            message.setObject(productMapper.mapToDto(productRepository.save(productMapper.mapToEntity(productDto))));
        } catch (Exception ex) {
            log.error("Error while saving product: {}", ex.getMessage());
            message.setMessage("Error saving the product.");
            message.setError(true);
        }
        return message;
    }

    /**
     * Updates an existing product.
     *
     * @param productDto the updated product details
     * @return a {@link GeneralMessageDto} containing the result of the operation
     */
    @Override
    public GeneralMessageDto updateProduct(ProductDto productDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            ProductDto existingProduct = productMapper.mapToDto(productRepository.findById(productDto.getId()).orElse(null));
            if (existingProduct != null) {
                message.setMessage("Product successfully updated.");
                ProductDto formattedProduct = Utils.compareOldNewObject(existingProduct, productDto);
                message.setObject(productMapper.mapToDto(productRepository.save(productMapper.mapToEntity(formattedProduct))));
            } else {
                message.setMessage("The specified product does not exist.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error updating product: {}", ex.getMessage());
            message.setMessage("Error updating the product.");
            message.setError(true);
        }
        return message;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId the product ID
     * @return a {@link GeneralMessageDto} containing the result of the operation
     */
    @Override
    public GeneralMessageDto deleteProduct(Long productId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            ProductDto product = productMapper.mapToDto(productRepository.findById(productId).orElse(null));
            if (product != null) {
                message.setMessage("Product successfully deleted.");
                productRepository.delete(productMapper.mapToEntity(product));
            } else {
                message.setMessage("The specified product does not exist.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error deleting product: {}", ex.getMessage());
            message.setMessage("Error deleting the product.");
            message.setError(true);
        }
        return message;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the product ID
     * @return a {@link GeneralMessageDto} containing the product details
     */
    @Override
    public GeneralMessageDto getProduct(Long productId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            ProductDto product = productMapper.mapToDto(productRepository.findById(productId).orElse(null));
            message.setMessage("Product successfully retrieved.");
            message.setObject(product);
        } catch (Exception ex) {
            log.error("Error retrieving product: {}", ex.getMessage());
            message.setMessage("Error retrieving the product.");
            message.setError(true);
        }
        return message;
    }
}
