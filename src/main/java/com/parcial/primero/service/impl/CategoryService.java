package com.parcial.primero.service.impl;

import com.parcial.primero.dto.CategoryDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.mapper.CategoryMapper;
import com.parcial.primero.repository.CategoryRepository;
import com.parcial.primero.service.ICategoryService;
import com.parcial.primero.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing categories.
 * <p>
 * This class handles category creation, updating, deletion, and retrieval.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public GeneralMessageDto createCategory(CategoryDto categoryDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            message.setMessage("Category successfully created.");
            message.setObject(categoryMapper.mapToDto(categoryRepository.save(categoryMapper.mapToEntity(categoryDto))));
        } catch (Exception ex) {
            log.error("Error while saving category: {}", ex.getMessage());
            message.setMessage("Error saving the category.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto updateCategory(CategoryDto categoryDto) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            CategoryDto existingCategory = categoryMapper.mapToDto(categoryRepository.findById(categoryDto.getId()).orElse(null));
            if (existingCategory != null) {
                message.setMessage("Category successfully updated.");
                CategoryDto formattedCategory = Utils.compareOldNewObject(existingCategory, categoryDto);
                message.setObject(categoryMapper.mapToDto(categoryRepository.save(categoryMapper.mapToEntity(formattedCategory))));
            } else {
                message.setMessage("Category not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error updating category: {}", ex.getMessage());
            message.setMessage("Error updating the category.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto deleteCategory(Long categoryId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            CategoryDto category = categoryMapper.mapToDto(categoryRepository.findById(categoryId).orElse(null));
            if (category != null) {
                message.setMessage("Category successfully deleted.");
                categoryRepository.delete(categoryMapper.mapToEntity(category));
            } else {
                message.setMessage("Category not found.");
                message.setError(true);
            }
        } catch (Exception ex) {
            log.error("Error deleting category: {}", ex.getMessage());
            message.setMessage("Error deleting the category.");
            message.setError(true);
        }
        return message;
    }

    @Override
    public GeneralMessageDto getCategory(Long categoryId) {
        GeneralMessageDto message = new GeneralMessageDto();
        try {
            CategoryDto category = categoryMapper.mapToDto(categoryRepository.findById(categoryId).orElse(null));
            message.setMessage("Category retrieved successfully.");
            message.setObject(category);
        } catch (Exception ex) {
            log.error("Error retrieving category: {}", ex.getMessage());
            message.setMessage("Error retrieving the category.");
            message.setError(true);
        }
        return message;
    }
}
