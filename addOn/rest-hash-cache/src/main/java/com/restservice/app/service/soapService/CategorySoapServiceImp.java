package com.restservice.app.service.soapService;

import com.restservice.app.dto.database.CategoryDto;
import com.restservice.app.repository.soapRepository.CategorySoapRepository;
import com.restservice.app.domain.cache.redis.CategoryCache;
import com.restservice.app.util.CastDto;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class CategorySoapServiceImp implements CategorySoapService {

    private final CategorySoapRepository categorySoapRepository;
    private final CastDto castDto;

    public CategorySoapServiceImp(CategorySoapRepository categorySoapRepository, CastDto castDto) {
        this.categorySoapRepository = categorySoapRepository;
        this.castDto = castDto;
    }

    @Override
    public CategoryCache getCategoryById(Long id) {
        return castDto.categoryFromCategoryDto(categorySoapRepository.getCategoryById(id));
    }

    @Override
    public CategoryCache getCategoryByName(String name) {
        return castDto.categoryFromCategoryDto(categorySoapRepository.getCategoryByName(name));
    }

    @Override
    public List<CategoryCache> getAllCategories() {
        List<CategoryDto> categoryDtos = categorySoapRepository.getAllCategories();
        List<CategoryCache> categories = new LinkedList<>();
        categoryDtos.forEach(c -> categories.add(castDto.categoryFromCategoryDto(c)));
        return categories;
    }

    @Override
    public CategoryCache createCategory(CategoryCache category) {
        CategoryDto categoryDto = castDto.categoryDtoFromCategory(category);
        categoryDto = categorySoapRepository.createCategory(categoryDto);
        return castDto.categoryFromCategoryDto(categoryDto);
    }

    @Override
    public CategoryCache updateCategory(CategoryCache category) {
        CategoryDto categoryDto = castDto.categoryDtoFromCategory(category);
        categoryDto = categorySoapRepository.updateCategory(categoryDto);
        return castDto.categoryFromCategoryDto(categoryDto);
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        return categorySoapRepository.deleteCategoryById(id);
    }

    @Override
    public List<CategoryCache> getCategoriesByItemsName(String name) {
        List<CategoryDto> categoryDtos = categorySoapRepository.getCategoriesByItemsName(name);
        List<CategoryCache> categories = new LinkedList<>();
        categoryDtos.forEach(c -> categories.add(castDto.categoryFromCategoryDto(c)));
        return categories;
    }

    @Override
    public List<CategoryCache> getCategoriesByItemsId(Long id) {
        List<CategoryDto> categoryDtos = categorySoapRepository.getCategoriesByItemsId(id);
        List<CategoryCache> categories = new LinkedList<>();
        categoryDtos.forEach(c -> categories.add(castDto.categoryFromCategoryDto(c)));
        return categories;
    }
}
