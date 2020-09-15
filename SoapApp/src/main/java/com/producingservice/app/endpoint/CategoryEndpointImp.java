package com.producingservice.app.endpoint;

import com.producingservice.app.service.endpoint.CategoryEndpointService;
import database.dto.app.producingservice.com.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Endpoint
public class CategoryEndpointImp implements CategoryEndpoint{

    private final Logger logger = LoggerFactory.getLogger(CategoryEndpointImp.class);
    private static final String NAMESPACE = "http://com.producingservice.app.dto.database";
    private final CategoryEndpointService categoryEndpointService;

    @Autowired
    public CategoryEndpointImp(CategoryEndpointService categoryEndpointService) {
        this.categoryEndpointService = categoryEndpointService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getCategoryByIdRequest")
    @ResponsePayload
    public GetCategoryByIdResponse getCategoryById(@RequestPayload GetCategoryByIdRequest request) {
        GetCategoryByIdResponse response = new GetCategoryByIdResponse();

        CategoryDto categoryDto = categoryEndpointService.findById(request.getId());
        response.setCategory(categoryDto);
        logger.info("IN getCategoryById - Sending category with id={}", categoryDto.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getCategoryByNameRequest")
    @ResponsePayload
    public GetCategoryByNameResponse getCategoryByName(@RequestPayload GetCategoryByNameRequest request) {
        GetCategoryByNameResponse response = new GetCategoryByNameResponse();

        CategoryDto categoryDto = categoryEndpointService.findByName(request.getName());
        response.setCategory(categoryDto);
        logger.info("IN getCategoryByName - Sending category with name={}", categoryDto.getName());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "createCategoryRequest")
    @ResponsePayload
    public CreateCategoryResponse createCategory(@RequestPayload CreateCategoryRequest request) {
        CreateCategoryResponse response = new CreateCategoryResponse();
        CategoryDto categoryDto = request.getCategory();

        CategoryDto savedCategoryDto = categoryEndpointService.save(categoryDto);
        response.setCreatedCategory(savedCategoryDto);
        logger.info("IN createCategory - category with generated id={} saved", savedCategoryDto.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "updateCategoryRequest")
    @ResponsePayload
    public UpdateCategoryResponse updateCategory(@RequestPayload UpdateCategoryRequest request) {
        UpdateCategoryResponse response = new UpdateCategoryResponse();
        CategoryDto categoryDto = request.getCategory();

        CategoryDto updatedCategoryDto = categoryEndpointService.update(categoryDto);
        response.setUpdatedCategory(updatedCategoryDto);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "deleteCategoryByIdRequest")
    @ResponsePayload
    public DeleteCategoryByIdResponse deleteCategoryById(@RequestPayload DeleteCategoryByIdRequest request) {
        DeleteCategoryByIdResponse response = new DeleteCategoryByIdResponse();

        response.setAnswer(categoryEndpointService.deleteById(request.getId()));
        logger.info("IN deleteCategoryById - category with id={} deleted", request.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllCategoriesRequest")
    @ResponsePayload
    public GetAllCategoriesResponse getAllCategories(@RequestPayload GetAllCategoriesRequest request) {
        GetAllCategoriesResponse response = new GetAllCategoriesResponse();

        List<CategoryDto> categoriesDto = categoryEndpointService.findAll();
        response.getCategories().addAll(categoriesDto);
        logger.info("IN getAllCategories - {} categories found and sent", categoriesDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllCategoriesByItemIdRequest")
    @ResponsePayload
    @Override
    public GetAllCategoriesByItemIdResponse getAllCategoriesByItemId(@RequestPayload GetAllCategoriesByItemIdRequest request) {
        GetAllCategoriesByItemIdResponse response = new GetAllCategoriesByItemIdResponse();

        List<CategoryDto> categoriesDto = categoryEndpointService.findAllByItemsId(request.getId());
        response.getCategories().addAll(categoriesDto);
        logger.info("IN getAllCategoriesByItemId - {} categories found and sent", categoriesDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllCategoriesByItemNameRequest")
    @ResponsePayload
    @Override
    public GetAllCategoriesByItemNameResponse getAllCategoriesByItemName(@RequestPayload GetAllCategoriesByItemNameRequest request) {
        GetAllCategoriesByItemNameResponse response = new GetAllCategoriesByItemNameResponse();

        List<CategoryDto> categoriesDto = categoryEndpointService.findAllByItemsName(request.getName());
        response.getCategories().addAll(categoriesDto);
        logger.info("IN getAllCategoriesByItemName - {} categories found and sent", categoriesDto.size());
        return response;
    }
}
