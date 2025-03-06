package com.parcial.primero.service;

import com.parcial.primero.dto.CategoryDto;
import com.parcial.primero.dto.GeneralMessageDto;

/**
 * Service interface for managing categories.
 * <p>
 * Provides methods for creating, updating, deleting, and retrieving categories.
 * </p>
 */
public interface ICategoryService {

    /**
     * Creates a new category.
     *
     * @param categoryDto The category DTO containing the category details.
     * @return A response message with status information.
     */
    GeneralMessageDto createCategory(CategoryDto categoryDto);

    /**
     * Updates an existing category.
     *
     * @param categoryDto The category DTO with updated details.
     * @return A response message with status information.
     */
    GeneralMessageDto updateCategory(CategoryDto categoryDto);

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return A response message with status information.
     */
    GeneralMessageDto deleteCategory(Long categoryId);

    /**
     * Retrieves a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return A response message containing the category details or an error message.
     */
    GeneralMessageDto getCategory(Long categoryId);
}
