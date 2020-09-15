package com.transcacheservice.cacheapp.repository.soapRepository;

import com.transcacheservice.cacheapp.dto.database.*;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface CategorySoapRepository {

    CategoryDto getCategoryById(Long id);

    CategoryDto getCategoryByName(String name);

    List<CategoryDto> getAllCategories();

    CategoryDto createCategory(CategoryDto category);

    CategoryDto updateCategory(CategoryDto category);

    boolean deleteCategoryById(Long id);

    List<CategoryDto> getCategoriesByItemsName(String name);

    List<CategoryDto> getCategoriesByItemsId(Long id);
}
