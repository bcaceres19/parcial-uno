package com.parcial.primero.service;

import com.parcial.primero.dto.CategoryDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.entity.CategoryEntity;
import com.parcial.primero.mapper.CategoryMapper;
import com.parcial.primero.repository.CategoryRepository;
import com.parcial.primero.service.impl.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testCreateCategorySuccess() {
        // Arrange: preparar objeto de entrada y dummy entity para simular el proceso
        CategoryDto inputDto = new CategoryDto();
        inputDto.setId(1L); // si es necesario asignar un id de prueba
        CategoryEntity dummyEntity = new CategoryEntity(); // representa la entidad mapeada

        // Stub de los mappers y repositorio
        when(categoryMapper.mapToEntity(inputDto)).thenReturn(dummyEntity);
        when(categoryRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(categoryMapper.mapToDto(dummyEntity)).thenReturn(inputDto);

        // Act
        GeneralMessageDto result = categoryService.createCategory(inputDto);

        // Assert
        assertFalse(result.isError());
        assertEquals("Category successfully created.", result.getMessage());
        assertEquals(inputDto, result.getObject());
    }

    @Test
    public void testUpdateCategorySuccess() {
        // Arrange
        CategoryDto inputDto = new CategoryDto();
        inputDto.setId(1L);
        // Simulamos que existe la categoría en el repositorio
        CategoryEntity dummyEntity = new CategoryEntity();
        CategoryDto existingDto = new CategoryDto();
        existingDto.setId(1L);
        // Simulación de la función de mapeo
        when(categoryRepository.findById(inputDto.getId())).thenReturn(Optional.of(dummyEntity));
        when(categoryMapper.mapToDto(dummyEntity)).thenReturn(existingDto);
        // Suponemos que Utils.compareOldNewObject devuelve el mismo objeto recibido (o modificado según lógica)
        // Para efectos de test, podemos asumir que devuelve inputDto
        CategoryDto comparedDto = inputDto;
        // Stub de la actualización: mapeo a entidad, guardado y conversión a DTO
        when(categoryMapper.mapToEntity(comparedDto)).thenReturn(dummyEntity);
        when(categoryRepository.save(dummyEntity)).thenReturn(dummyEntity);
        when(categoryMapper.mapToDto(dummyEntity)).thenReturn(comparedDto);

        // Act
        GeneralMessageDto result = categoryService.updateCategory(inputDto);

        // Assert
        assertFalse(result.isError());
        assertEquals("Category successfully updated.", result.getMessage());
        assertEquals(comparedDto, result.getObject());
    }

    @Test
    public void testUpdateCategoryNotFound() {
        // Arrange
        CategoryDto inputDto = new CategoryDto();
        inputDto.setId(1L);
        when(categoryRepository.findById(inputDto.getId())).thenReturn(Optional.empty());

        // Act
        GeneralMessageDto result = categoryService.updateCategory(inputDto);

        // Assert
        assertTrue(result.isError());
        assertEquals("Category not found.", result.getMessage());
        assertNull(result.getObject());
    }

    @Test
    public void testDeleteCategorySuccess() {
        // Arrange
        Long categoryId = 1L;
        CategoryEntity dummyEntity = new CategoryEntity();
        CategoryDto existingDto = new CategoryDto();
        existingDto.setId(categoryId);
        // Simulamos que la categoría existe en el repositorio
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(dummyEntity));
        when(categoryMapper.mapToDto(dummyEntity)).thenReturn(existingDto);
        // Para delete no es necesario stubbing adicional ya que se elimina

        // Act
        GeneralMessageDto result = categoryService.deleteCategory(categoryId);

        // Assert
        assertFalse(result.isError());
        assertEquals("Category successfully deleted.", result.getMessage());
    }

    @Test
    public void testDeleteCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act
        GeneralMessageDto result = categoryService.deleteCategory(categoryId);

        // Assert
        assertTrue(result.isError());
        assertEquals("Category not found.", result.getMessage());
    }

    @Test
    public void testGetCategorySuccess() {
        // Arrange
        Long categoryId = 1L;
        CategoryEntity dummyEntity = new CategoryEntity();
        CategoryDto existingDto = new CategoryDto();
        existingDto.setId(categoryId);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(dummyEntity));
        when(categoryMapper.mapToDto(dummyEntity)).thenReturn(existingDto);

        // Act
        GeneralMessageDto result = categoryService.getCategory(categoryId);

        // Assert
        assertFalse(result.isError());
        assertEquals("Category retrieved successfully.", result.getMessage());
        assertEquals(existingDto, result.getObject());
    }
}
