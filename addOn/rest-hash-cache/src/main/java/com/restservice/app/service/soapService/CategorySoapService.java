package com.restservice.app.service.soapService;

import com.restservice.app.domain.cache.redis.CategoryCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface CategorySoapService {

    CategoryCache getCategoryById(Long id);

    CategoryCache getCategoryByName(String name);

    List<CategoryCache> getAllCategories();

    CategoryCache createCategory(CategoryCache categoryDto);

    CategoryCache updateCategory(CategoryCache categoryDto);

    boolean deleteCategoryById(Long id);

    List<CategoryCache> getCategoriesByItemsName(String name);

    List<CategoryCache> getCategoriesByItemsId(Long id);

}
