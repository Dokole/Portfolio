package com.transcacheservice.cacheapp.repository.soapRepository;

import com.transcacheservice.cacheapp.dto.database.*;
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
public class ItemSoapClient extends WebServiceGatewaySupport implements ItemSoapRepository {

    private static final Logger logger = LoggerFactory.getLogger(ItemSoapClient.class);

    public ItemDto getItemById(Long id) {
        GetItemByIdRequest request = new GetItemByIdRequest();
        request.setId(id);
        GetItemByIdResponse response = (GetItemByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ItemDto itemDto = response.getItem();
        logger.info("IN getItemById - item successfully found by id={}", id);
        return itemDto;
    }

    public ItemDto createItem(ItemDto item) {
        CreateItemRequest request = new CreateItemRequest();
        request.setItem(item);

        CreateItemResponse response = (CreateItemResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ItemDto itemDto = response.getCreatedItem();
        logger.info("IN createItem - item with id={} successfully created", itemDto.getId());
        return itemDto;
    }

    public ItemDto updateItem(ItemDto item) {
        UpdateItemRequest request = new UpdateItemRequest();
        request.setItem(item);

        UpdateItemResponse response = (UpdateItemResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ItemDto itemDto = response.getUpdatedItem();
        logger.info("IN updateItem - item with id={} successfully updated", itemDto.getId());
        return itemDto;
    }

    public boolean deleteItemById(Long id) {
        DeleteItemByIdRequest request = new DeleteItemByIdRequest();
        request.setId(id);

        DeleteItemByIdResponse response = (DeleteItemByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        boolean answer = response.isAnswer();
        logger.info("IN deleteItemById - item with id={} successfully deleted", id);
        return answer;
    }

    public List<ItemDto> getAllItems() {
        GetAllItemsRequest request = new GetAllItemsRequest();
        GetAllItemsResponse response = (GetAllItemsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getAllItems - {} items successfully received", itemDto.size());
        return itemDto;
    }

    @Override
    public ItemDto getItemByName(String name) {
        GetItemByNameRequest request = new GetItemByNameRequest();
        request.setName(name);
        GetItemByNameResponse response = (GetItemByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ItemDto itemDto = response.getItem();
        logger.info("IN getItemByName -item successfully found by name={}", itemDto.getName());
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByBrandId(Long id) {
        GetItemsByBrandIdRequest request = new GetItemsByBrandIdRequest();
        request.setId(id);
        GetItemsByBrandIdResponse response = (GetItemsByBrandIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByBrandId - {} items successfully found by brand's id={}", itemDto.size(), id);
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByManufacturerId(Long id) {
        GetItemsByManufacturerIdRequest request = new GetItemsByManufacturerIdRequest();
        request.setId(id);

        GetItemsByManufacturerIdResponse response = (GetItemsByManufacturerIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByManufacturerId - {} items successfully found by manufacturer's id={}",
                itemDto.size(), id);
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByBrandName(String name) {
        GetItemsByBrandNameRequest request = new GetItemsByBrandNameRequest();
        request.setName(name);

        GetItemsByBrandNameResponse response = (GetItemsByBrandNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByBrandName - {} items successfully received", itemDto.size());
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByManufacturerName(String name) {
        GetItemsByManufacturerNameRequest request = new GetItemsByManufacturerNameRequest();
        request.setName(name);

        GetItemsByManufacturerNameResponse response = (GetItemsByManufacturerNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByManufacturerName - {} items successfully received", itemDto.size());
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByCategoriesId(Long id) {
        GetItemsByCategoryIdRequest request = new GetItemsByCategoryIdRequest();
        request.setId(id);

        GetItemsByCategoryIdResponse response = (GetItemsByCategoryIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByCategoriesId - {} items successfully found by manufacturer's id={}",
                itemDto.size(), id);
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByCategoriesName(String name) {
        GetItemsByCategoryNameRequest request = new GetItemsByCategoryNameRequest();
        request.setName(name);

        GetItemsByCategoryNameResponse response = (GetItemsByCategoryNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByCategoriesName - {} items successfully received", itemDto.size());
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByDescriptionLike(String desc) {
        GetItemsByDescriptionRequest request = new GetItemsByDescriptionRequest();
        request.setDescription(desc);

        GetItemsByDescriptionResponse response = (GetItemsByDescriptionResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByDescriptionLike - {} items successfully received", itemDto.size());
        return itemDto;
    }

    @Override
    public List<ItemDto> getItemsByPriceBetween(int low, int high) {
        GetItemsByPriceRangeRequest request = new GetItemsByPriceRangeRequest();
        request.setLowValue(low);
        request.setHighValue(high);

        GetItemsByPriceRangeResponse response = (GetItemsByPriceRangeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ItemDto> itemDto = response.getItems();
        logger.info("IN getItemsByPriceBetween - {} items successfully received", itemDto.size());
        return itemDto;
    }
}
