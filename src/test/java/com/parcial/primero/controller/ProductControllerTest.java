package com.parcial.primero.controller;

import com.parcial.primero.dto.ProductDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.service.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private IProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testCreateProductSuccess() {
        ProductDto dto = new ProductDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Product created successfully");

        when(productService.createProduct(any(ProductDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = productController.createProduct(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product created successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testUpdateProductSuccess() {
        ProductDto dto = new ProductDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Product updated successfully");

        when(productService.updateProduct(any(ProductDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = productController.updateProduct(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product updated successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testDeleteProductSuccess() {
        Long productId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Product deleted successfully");

        when(productService.deleteProduct(productId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testGetProductSuccess() {
        Long productId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Product retrieved successfully");

        when(productService.getProduct(productId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = productController.getProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product retrieved successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }
}
