package com.parcial.primero.controller;

import com.parcial.primero.dto.CategoryDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.service.ICategoryService;
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
public class CategoryControllerTest {

    @Mock
    private ICategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void testCreateCategorySuccess() {
        CategoryDto categoryDto = new CategoryDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Category created successfully");

        when(categoryService.createCategory(any(CategoryDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = categoryController.createCategory(categoryDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category created successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testUpdateCategorySuccess() {
        CategoryDto categoryDto = new CategoryDto();
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Category updated successfully");

        when(categoryService.updateCategory(any(CategoryDto.class))).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = categoryController.updateCategory(categoryDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category updated successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testDeleteCategorySuccess() {
        Long categoryId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Category deleted successfully");

        when(categoryService.deleteCategory(categoryId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = categoryController.deleteCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category deleted successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }

    @Test
    public void testGetCategorySuccess() {
        Long categoryId = 1L;
        GeneralMessageDto gm = new GeneralMessageDto();
        gm.setError(false);
        gm.setMessage("Category retrieved successfully");

        when(categoryService.getCategory(categoryId)).thenReturn(gm);

        ResponseEntity<GeneralMessageDto> response = categoryController.getCategory(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category retrieved successfully", response.getBody().getMessage());
        assertEquals(false, response.getBody().isError());
    }
}
