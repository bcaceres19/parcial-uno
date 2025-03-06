package com.parcial.primero.controller;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.ProductDto;
import com.parcial.primero.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that manages operations related to products.
 * <p>
 * This controller provides endpoints to create, update, delete, and retrieve products.
 * </p>
 *
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    /**
     * Creates a new product.
     *
     * @param productDto The product data transfer object containing product details.
     * @return A response entity containing a general message with status information.
     */
    @PostMapping
    public ResponseEntity<GeneralMessageDto> createProduct(@RequestBody ProductDto productDto) {
        GeneralMessageDto response = productService.createProduct(productDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Updates an existing product.
     *
     * @param productDto The product data transfer object containing updated details.
     * @return A response entity containing a general message with status information.
     */
    @PutMapping
    public ResponseEntity<GeneralMessageDto> updateProduct(@RequestBody ProductDto productDto) {
        GeneralMessageDto response = productService.updateProduct(productDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to be deleted.
     * @return A response entity containing a general message with status information.
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<GeneralMessageDto> deleteProduct(@PathVariable Long productId) {
        GeneralMessageDto response = productService.deleteProduct(productId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return A response entity containing the product details or an error message.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<GeneralMessageDto> getProduct(@PathVariable Long productId) {
        GeneralMessageDto response = productService.getProduct(productId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }
}
