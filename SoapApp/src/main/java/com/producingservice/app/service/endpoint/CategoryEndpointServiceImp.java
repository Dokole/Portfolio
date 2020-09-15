package com.producingservice.app.service.endpoint;

import com.producingservice.app.domain.Category;
import com.producingservice.app.exceptions.BadRequestException;
import com.producingservice.app.exceptions.NotFoundException;
import com.producingservice.app.service.data.CategoryDataService;
import com.producingservice.app.util.CastToDtoUtil;
import database.dto.app.producingservice.com.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("categoryEndpointService")
public class CategoryEndpointServiceImp implements CategoryEndpointService {

    private final Logger logger = LoggerFactory.getLogger(CategoryEndpointServiceImp.class);

    private final CategoryDataService categoryDataService;
    private final CastToDtoUtil castToDtoUtil;

    @Autowired
    public CategoryEndpointServiceImp(CategoryDataService categoryDataService, CastToDtoUtil castToDtoUtil) {
        this.categoryDataService = categoryDataService;
        this.castToDtoUtil = castToDtoUtil;
    }

    @Override
    public List<CategoryDto> findAll() {
        Set<Category> categories;
        List<CategoryDto> categoriesDto = new LinkedList<>();
        categories = categoryDataService.findAll();
        if (categories.isEmpty()) {
            logger.error("IN findAll - No categories in the database");
            throw new NotFoundException("No categories found in the database.");
        }
        categories.forEach(c -> categoriesDto.add(castToDtoUtil.categoryToCategoryDto(c)));
        return categoriesDto;
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = null;
        category = categoryDataService.findById(id);

        if (category == null) {
            logger.warn("IN findById - no category found by id={}", id);
            throw new NotFoundException("No category found by id=" + id);
        }
        return castToDtoUtil.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto findByName(String name) {
        Category category = null;
        category = categoryDataService.findByName(name);
        if (category == null) {
            logger.warn("IN findByName - no category found by name={}", name);
            throw new NotFoundException("No category found by name=" + name);
        }
        return castToDtoUtil.categoryToCategoryDto(category);
    }

    @Override
    public boolean deleteById(Long id) {
        categoryDataService.deleteById(id);
        return true;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            throw new BadRequestException("Id=" + categoryDto.getId() + " should be null to create a category. Can't be saved.");
        }
        if (categoryDataService.existsByName(categoryDto.getName())) {
            throw new BadRequestException("Category with name=" + categoryDto.getName() + " already exists");
        }
        Category category = castToDtoUtil.categoryDtoToCategory(categoryDto);
        category = categoryDataService.save(category);
        categoryDto = castToDtoUtil.categoryToCategoryDto(category);
        return categoryDto;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        if (!existsById(categoryDto.getId())) {
            throw new BadRequestException("Can't update category with id=" + categoryDto.getId() + ". It doesn't exists");
        }
        Category category = castToDtoUtil.categoryDtoToCategory(categoryDto);
        category = categoryDataService.save(category);

        categoryDto = castToDtoUtil.categoryToCategoryDto(category);
        return categoryDto;
    }

    @Override
    public boolean existsById(Long id) {
        return categoryDataService.existsById(id);
    }

    @Override
    public List<CategoryDto> findAllByItemsName(String name) {
        Set<Category> categories;
        List<CategoryDto> categoryDtos = new LinkedList<>();
        categories = categoryDataService.findAllByItemsName(name);
        if (categories.isEmpty()) {
            logger.error("IN findAllByItemsName - no categories found in the database by item name={}", name);
            throw new NotFoundException("No categories found in the database by item name=" + name);
        }
        categories.forEach(i -> categoryDtos.add(castToDtoUtil.categoryToCategoryDto(i)));
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> findAllByItemsId(Long id) {
        Set<Category> categories;
        List<CategoryDto> categoryDtos = new LinkedList<>();
        categories = categoryDataService.findAllByItemsId(id);
        if (categories.isEmpty()) {
            logger.error("IN findAllByItemsId - no categories found in the database by item id={}", id);
            throw new NotFoundException("No categories found in the database by item id=" + id);
        }
        categories.forEach(i -> categoryDtos.add(castToDtoUtil.categoryToCategoryDto(i)));
        return categoryDtos;
    }
}
