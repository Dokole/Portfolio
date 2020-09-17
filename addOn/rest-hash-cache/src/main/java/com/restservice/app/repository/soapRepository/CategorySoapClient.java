package com.restservice.app.repository.soapRepository;

import com.restservice.app.dto.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Repository
public class CategorySoapClient extends WebServiceGatewaySupport implements CategorySoapRepository {

    private static Logger logger = LoggerFactory.getLogger(CategorySoapClient.class);

    @Override
    public CategoryDto getCategoryById(Long id) {
        GetCategoryByIdRequest request = new GetCategoryByIdRequest();
        request.setId(id);

        GetCategoryByIdResponse response = (GetCategoryByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CategoryDto categoryDto = response.getCategory();
        logger.info("IN getCategoryById - category successfully found by id={}", id);
        return categoryDto;

    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        GetCategoryByNameRequest request = new GetCategoryByNameRequest();
        request.setName(name);

        GetCategoryByNameResponse response = (GetCategoryByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CategoryDto categoryDto = response.getCategory();
        logger.info("IN getCategoryByName - category successfully found by name={}", categoryDto.getName());
        return categoryDto;

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        GetAllCategoriesRequest request = new GetAllCategoriesRequest();

        GetAllCategoriesResponse response = (GetAllCategoriesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<CategoryDto> categoriesDto = response.getCategories();
        logger.info("IN getAllCategories - {} categories successfully received", categoriesDto.size());
        return categoriesDto;
    }

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setCategory(category);

        CreateCategoryResponse response = (CreateCategoryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CategoryDto categoryDto = response.getCreatedCategory();
        logger.info("IN createCategory - category with id={} successfully created", categoryDto.getId());
        return categoryDto;

    }

    @Override
    public CategoryDto updateCategory(CategoryDto category) {
        UpdateCategoryRequest request = new UpdateCategoryRequest();
        request.setCategory(category);

        UpdateCategoryResponse response = (UpdateCategoryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CategoryDto categoryDto = response.getUpdatedCategory();
        logger.info("IN updateCategory - category with id={} successfully updated", categoryDto.getId());
        return categoryDto;

    }

    @Override
    public boolean deleteCategoryById(Long id) {
        DeleteCategoryByIdRequest request = new DeleteCategoryByIdRequest();
        request.setId(id);

        DeleteCategoryByIdResponse response = (DeleteCategoryByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        boolean answer = response.isAnswer();
        logger.info("IN deleteCategoryById - category with id={} successfully deleted", id);
        return answer;
    }

    @Override
    public List<CategoryDto> getCategoriesByItemsName(String name) {
        GetAllCategoriesByItemNameRequest request = new GetAllCategoriesByItemNameRequest();
        request.setName(name);
        GetAllCategoriesByItemNameResponse response = (GetAllCategoriesByItemNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<CategoryDto> categoriesDto = response.getCategories();
        logger.info("IN getCategoriesByItemsName - {} categories successfully received", categoriesDto.size());
        return categoriesDto;
    }

    @Override
    public List<CategoryDto> getCategoriesByItemsId(Long id) {
        GetAllCategoriesByItemIdRequest request = new GetAllCategoriesByItemIdRequest();
        request.setId(id);
        GetAllCategoriesByItemIdResponse response = (GetAllCategoriesByItemIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<CategoryDto> categoriesDto = response.getCategories();
        logger.info("IN getCategoriesByItemsId - {} categories successfully received", categoriesDto.size());
        return categoriesDto;
    }
}
