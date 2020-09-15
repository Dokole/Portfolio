package com.producingservice.app.endpoint;


import com.producingservice.app.service.endpoint.ItemEndpointService;
import database.dto.app.producingservice.com.GetItemByIdResponse;
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
public class ItemEndpointImp implements ItemEndpoint {

    private final Logger logger = LoggerFactory.getLogger(ItemEndpointImp.class);
    private static final String NAMESPACE = "http://com.producingservice.app.dto.database";
    private final ItemEndpointService itemEndpointService;

    @Autowired
    public ItemEndpointImp(ItemEndpointService itemEndpointService) {
        this.itemEndpointService = itemEndpointService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemByIdRequest")
    @ResponsePayload
    public GetItemByIdResponse getItemById(@RequestPayload GetItemByIdRequest request) {
        GetItemByIdResponse response = new GetItemByIdResponse();
      
        ItemDto itemDto = itemEndpointService.findById(request.getId());
        response.setItem(itemDto);
        logger.info("IN getItemById - Sending item with id={}", itemDto.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemByNameRequest")
    @ResponsePayload
    public GetItemByNameResponse getItemByName(@RequestPayload GetItemByNameRequest request) {
        GetItemByNameResponse response = new GetItemByNameResponse();

        ItemDto itemDto = itemEndpointService.findByName(request.getName());
        logger.info("IN getItemByName - Item found by name {}", request.getName());
        response.setItem(itemDto);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "createItemRequest")
    @ResponsePayload
    public CreateItemResponse createItem(@RequestPayload CreateItemRequest request) {
        CreateItemResponse response = new CreateItemResponse();
        ItemDto itemDto = request.getItem();
        ItemDto createdItem = itemEndpointService.save(itemDto);
        response.setCreatedItem(createdItem);
        logger.info("IN createItem - Item with generated id={} saved", createdItem.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "updateItemRequest")
    @ResponsePayload
    public UpdateItemResponse updateItem(@RequestPayload UpdateItemRequest request) {
        UpdateItemResponse response = new UpdateItemResponse();
        ItemDto itemDto = request.getItem();

        ItemDto updatedItem = itemEndpointService.update(itemDto);
        response.setUpdatedItem(updatedItem);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "deleteItemByIdRequest")
    @ResponsePayload
    public DeleteItemByIdResponse deleteItemById(@RequestPayload DeleteItemByIdRequest request) {
        DeleteItemByIdResponse response = new DeleteItemByIdResponse();

        response.setAnswer(itemEndpointService.deleteById(request.getId()));
        logger.info("IN deleteItemById - item with id={} deleted", request.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllItemsRequest")
    @ResponsePayload
    public GetAllItemsResponse getAllItems(@RequestPayload GetAllItemsRequest request) {
        GetAllItemsResponse response = new GetAllItemsResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAll();
        response.getItems().addAll(itemsDto);
        logger.info("IN getAllItems - {} items found and sent", itemsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByBrandIdRequest")
    @ResponsePayload
    public GetItemsByBrandIdResponse getItemsByBrandId(@RequestPayload GetItemsByBrandIdRequest request) {
        GetItemsByBrandIdResponse response = new GetItemsByBrandIdResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByBrandDetailsId(request.getId());
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByBrandId - {} items found and sent", itemsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByManufacturerIdRequest")
    @ResponsePayload
    public GetItemsByManufacturerIdResponse getItemsByManufacturerId(@RequestPayload GetItemsByManufacturerIdRequest request) {
        GetItemsByManufacturerIdResponse response = new GetItemsByManufacturerIdResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByManufacturerId(request.getId());
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByManufacturerId - {} items found and sent", itemsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByCategoryIdRequest")
    @ResponsePayload
    @Override
    public GetItemsByCategoryIdResponse getItemsByCategoryId(@RequestPayload GetItemsByCategoryIdRequest request) {
        GetItemsByCategoryIdResponse response = new GetItemsByCategoryIdResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByCategoriesId(request.getId());
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByCategoryId - {} items found and sent", itemsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByBrandNameRequest")
    @ResponsePayload
    @Override
    public GetItemsByBrandNameResponse getItemsByBrandName(@RequestPayload GetItemsByBrandNameRequest request) {
        GetItemsByBrandNameResponse response = new GetItemsByBrandNameResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByBrandDetailsName(request.getName());
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByBrandName - {} items found and sent", itemsDto.size());
        return response;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByManufacturerNameRequest")
    @ResponsePayload
    public GetItemsByManufacturerNameResponse getItemsByManufacturerName(@RequestPayload GetItemsByManufacturerNameRequest request) {
        GetItemsByManufacturerNameResponse response = new GetItemsByManufacturerNameResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByManufacturerName(request.getName());
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByManufacturerName - {} items found and sent", itemsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByCategoryNameRequest")
    @ResponsePayload
    @Override
    public GetItemsByCategoryNameResponse getItemsByCategoryName(@RequestPayload GetItemsByCategoryNameRequest request) {
        GetItemsByCategoryNameResponse response = new GetItemsByCategoryNameResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByCategoriesName(request.getName());
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByCategoryName - {} items found and sent", itemsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByDescriptionRequest")
    @ResponsePayload
    @Override
    public GetItemsByDescriptionResponse getItemsByDescription(@RequestPayload GetItemsByDescriptionRequest request) {
        GetItemsByDescriptionResponse response = new GetItemsByDescriptionResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByDescriptionLike("%" + request.getDescription() + "%");
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByDescription - {} items found and sent", itemsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemsByPriceRangeRequest")
    @ResponsePayload
    @Override
    public GetItemsByPriceRangeResponse getItemsByPriceRange(@RequestPayload GetItemsByPriceRangeRequest request) {
        GetItemsByPriceRangeResponse response = new GetItemsByPriceRangeResponse();

        List<ItemDto> itemsDto = itemEndpointService.findAllByPriceBetween(request.getLowValue(), request.getHighValue());
        response.getItems().addAll(itemsDto);
        logger.info("IN getItemsByPriceRange - {} items found and sent", itemsDto.size());
        return response;
    }
}
