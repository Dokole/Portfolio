package com.transcacheservice.cacheapp.service.databaseService;

import com.transcacheservice.cacheapp.domain.cache.redis.containers.CategoriesCache;
import com.transcacheservice.cacheapp.exceptions.BadRequestException;
import com.transcacheservice.cacheapp.exceptions.NotFoundException;
import com.transcacheservice.cacheapp.domain.cache.redis.CategoryCache;
import com.transcacheservice.cacheapp.service.cacheService.CategoryCacheService;
import com.transcacheservice.cacheapp.service.soapService.CategorySoapService;
import com.transcacheservice.cacheapp.util.CacheIdNamer;
import liquibase.pro.packaged.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class CategoryDatabaseServiceImp implements CategoryDatabaseService {

    private final Logger logger = LoggerFactory.getLogger(CategoryDatabaseServiceImp.class);

    private final CategoryCacheService categoryCacheService;
    private final CategorySoapService categorySoapService;
    private final CacheIdNamer cacheIdNamer;

    @Autowired
    public CategoryDatabaseServiceImp(CategoryCacheService categoryCacheService, CategorySoapService categorySoapService, CacheIdNamer cacheIdNamer) {
        this.categoryCacheService = categoryCacheService;
        this.categorySoapService = categorySoapService;
        this.cacheIdNamer = cacheIdNamer;
    }

    @Override
    public CategoryCache getCategoryById(String id) {
        try {
            CategoriesCache categories = categoryCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
            if(categories == null) {
                categories = new CategoriesCache();
                categories.getCategories().add(categorySoapService.getCategoryById(Long.parseLong(id)));
                categories.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
                categoryCacheService.save(categories);
            }
            return categories.getCategories().get(0);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public CategoryCache getCategoryByName(String name) {
        CategoriesCache categories = categoryCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if(categories == null) {
            categories = new CategoriesCache();
            categories.getCategories().add(categorySoapService.getCategoryByName(name));
            categories.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            categoryCacheService.save(categories);
        }
        return categories.getCategories().get(0);
    }

    @Override
    public CategoryCache createCategory(CategoryCache category) {

        categoryCacheService.invalidateRelatedCaches();
        CategoryCache createdCategory = categorySoapService.createCategory(category);
        categoryCacheService.invalidateRelatedCachesDelay();

        return createdCategory;
    }

    @Override
    public CategoryCache updateCategory(CategoryCache category) {

        categoryCacheService.invalidateRelatedCaches();
        CategoryCache updatedCategory = categorySoapService.updateCategory(category);
        categoryCacheService.invalidateRelatedCachesDelay();

        return updatedCategory;
    }

    @Override
    public boolean deleteCategoryById(String id) {
        categoryCacheService.invalidateRelatedCaches();
        try {
            if(categorySoapService.deleteCategoryById(Long.parseLong(id))) {
                categoryCacheService.invalidateRelatedCachesDelay();
                return true;
            }   else return false;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<CategoryCache> getAllCategories() {
        List<CategoryCache> results = categorySoapService.getAllCategories();
        if(results.isEmpty()) {
            throw new NotFoundException("No categories found");
        }
        return results;
    }



    @Override
    public List<CategoryCache> getAllCategoriesByItemsId(String id) {
        CategoriesCache categoriesCache = categoryCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
        if(categoriesCache == null) {
            categoriesCache = new CategoriesCache();
            categoriesCache.setCategories(categorySoapService.getCategoriesByItemsId(Long.parseLong(id)));
            categoriesCache.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
            categoryCacheService.save(categoriesCache);
        }
        return categoriesCache.getCategories();
    }

    @Override
    public List<CategoryCache> getAllCategoriesByItemsName(String name) {
        CategoriesCache categoriesCache = categoryCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if(categoriesCache == null) {
            categoriesCache = new CategoriesCache();
            categoriesCache.setCategories(categorySoapService.getCategoriesByItemsName(name));
            categoriesCache.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            categoryCacheService.save(categoriesCache);
        }
        return categoriesCache.getCategories();
    }

}
