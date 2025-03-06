package com.parcial.primero.controller;

import com.parcial.primero.dto.CategoryDto;
import com.parcial.primero.dto.GeneralMessageDto;
import com.parcial.primero.service.ICategoryService;
import com.parcial.primero.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that manages operations related to categories.
 * <p>
 * This controller provides endpoints to create, update, delete, and retrieve categories.
 * </p>
 *
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    /**
     * Creates a new category.
     *
     * @param categoryDto The category data transfer object containing the category details.
     * @return A response entity containing a general message with status information.
     */
    @PostMapping
    public ResponseEntity<GeneralMessageDto> createCategory(@RequestBody CategoryDto categoryDto) {
        GeneralMessageDto response = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Updates an existing category.
     *
     * @param categoryDto The category data transfer object containing updated details.
     * @return A response entity containing a general message with status information.
     */
    @PutMapping
    public ResponseEntity<GeneralMessageDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        GeneralMessageDto response = categoryService.updateCategory(categoryDto);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return A response entity containing a general message with status information.
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<GeneralMessageDto> deleteCategory(@PathVariable Long categoryId) {
        GeneralMessageDto response = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return A response entity containing the category details or an error message.
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<GeneralMessageDto> getCategory(@PathVariable Long categoryId) {
        GeneralMessageDto response = categoryService.getCategory(categoryId);
        return ResponseEntity.status(response.isError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK).body(response);
    }
}
