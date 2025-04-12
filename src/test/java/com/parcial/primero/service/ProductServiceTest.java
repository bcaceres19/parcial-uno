package com.parcial.primero.service;

import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.dto.ProductDto;
import com.parcial.primero.entity.ProductEntity;
import com.parcial.primero.mapper.ProductMapper;
import com.parcial.primero.repository.ProductRepository;
import com.parcial.primero.service.impl.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProductSuccess() {
        ProductDto inputDto = new ProductDto();


        inputDto.setId(1L);
        ProductEntity dummyEntity = new ProductEntity();

        when(productMapper.mapToEntity(inputDto)).thenReturn(dummyEntity);
        when(productRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(productMapper.mapToDto(dummyEntity)).thenReturn(inputDto);

        GeneralMessageDto result = productService.createProduct(inputDto);

        assertFalse(result.isError());
        assertEquals("Product successfully created.", result.getMessage());
        assertEquals(inputDto, result.getObject());
    }

    @Test
    public void testUpdateProductSuccess() {
        ProductDto inputDto = new ProductDto();
        inputDto.setId(1L);
        ProductEntity dummyEntity = new ProductEntity();
        ProductDto existingDto = new ProductDto();
        existingDto.setId(1L);

        when(productRepository.findById(inputDto.getId())).thenReturn(Optional.of(dummyEntity));
        when(productMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        // Suponiendo que Utils.compareOldNewObject devuelve inputDto para efectos de prueba
        ProductDto comparedDto = inputDto;
        when(productMapper.mapToEntity(comparedDto)).thenReturn(dummyEntity);
        when(productRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(productMapper.mapToDto(dummyEntity)).thenReturn(comparedDto);

        GeneralMessageDto result = productService.updateProduct(inputDto);

        assertFalse(result.isError());
        assertEquals("Product successfully updated.", result.getMessage());
        assertEquals(comparedDto, result.getObject());
    }

    @Test
    public void testUpdateProductNotFound() {
        ProductDto inputDto = new ProductDto();
        inputDto.setId(1L);
        when(productRepository.findById(inputDto.getId())).thenReturn(Optional.empty());

        GeneralMessageDto result = productService.updateProduct(inputDto);

        assertTrue(result.isError());
        assertEquals("The specified product does not exist.", result.getMessage());
        assertNull(result.getObject());
    }

    @Test
    public void testDeleteProductSuccess() {
        Long productId = 1L;
        ProductEntity dummyEntity = new ProductEntity();
        ProductDto existingDto = new ProductDto();
        existingDto.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(dummyEntity));
        when(productMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        GeneralMessageDto result = productService.deleteProduct(productId);

        assertFalse(result.isError());
        assertEquals("Product successfully deleted.", result.getMessage());
    }

    @Test
    public void testDeleteProductNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        GeneralMessageDto result = productService.deleteProduct(productId);

        assertTrue(result.isError());
        assertEquals("The specified product does not exist.", result.getMessage());
    }

    @Test
    public void testGetProductSuccess() {
        Long productId = 1L;
        ProductEntity dummyEntity = new ProductEntity();
        ProductDto existingDto = new ProductDto();
        existingDto.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(dummyEntity));
        when(productMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        GeneralMessageDto result = productService.getProduct(productId);

        assertFalse(result.isError());
        assertEquals("Product successfully retrieved.", result.getMessage());
        assertEquals(existingDto, result.getObject());
    }
}
