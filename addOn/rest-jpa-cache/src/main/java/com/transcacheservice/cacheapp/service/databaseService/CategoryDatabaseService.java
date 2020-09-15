package com.transcacheservice.cacheapp.service.databaseService;

import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface CategoryDatabaseService {
    CategoryCache getCategoryById(String id);

    CategoryCache getCategoryByName(String name);

    List<CategoryCache> getAllCategoriesByItemsId(String id);

    List<CategoryCache> getAllCategoriesByItemsName(String name);

    CategoryCache createCategory(CategoryCache category);

    CategoryCache updateCategory(CategoryCache category);

    boolean deleteCategoryById(String id);

    List<CategoryCache> getAllCategories();
}
