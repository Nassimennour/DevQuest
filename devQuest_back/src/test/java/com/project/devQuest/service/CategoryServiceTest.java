package com.project.devQuest.service;

import com.project.devQuest.dto.CategoryDTO;
import com.project.devQuest.model.Category;
import com.project.devQuest.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void whenGetCategoryById_thenReturnCategory() {
        Category category = new Category();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.getCategoryById(1L);

        assertNotNull(foundCategory);
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void whenGetCategoryByName_thenReturnCategory() {
        Category category = new Category();
        when(categoryRepository.findByName("Test Category")).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.getCategoryByName("Test Category");

        assertNotNull(foundCategory);
        verify(categoryRepository, times(1)).findByName("Test Category");
    }

    @Test
    public void whenGetAllCategories_thenReturnCategoryList() {
        Category category = new Category();
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));

        List<Category> categoryList = categoryService.getAllCategories();

        assertEquals(1, categoryList.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void whenGetCategoryByParentCategoryId_thenReturnCategoryList() {
        Category category = new Category();
        when(categoryRepository.findByParentCategoryId(1L)).thenReturn(Collections.singletonList(category));

        List<Category> categoryList = categoryService.getCategoryByParentCategoryId(1L);

        assertEquals(1, categoryList.size());
        verify(categoryRepository, times(1)).findByParentCategoryId(1L);
    }

    @Test
    public void whenCreateCategory_thenReturnSavedCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Test Category");
        categoryDTO.setParentCategoryId(1L);

        Category category = new Category();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category savedCategory = categoryService.createCategory(categoryDTO);

        assertNotNull(savedCategory);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    public void whenUpdateCategory_thenReturnUpdatedCategory() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.save(category)).thenReturn(category);

        Category updatedCategory = categoryService.updateCategory(category);

        assertNotNull(updatedCategory);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void whenDeleteCategory_thenVerifyDeletion() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteAllCategories_thenVerifyDeletion() {
        doNothing().when(categoryRepository).deleteAll();

        categoryService.deleteAllCategories();

        verify(categoryRepository, times(1)).deleteAll();
    }
}