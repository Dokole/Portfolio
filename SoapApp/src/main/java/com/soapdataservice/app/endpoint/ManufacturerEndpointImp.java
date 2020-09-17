package com.soapdataservice.app.endpoint;


import com.soapdataservice.app.service.endpoint.ManufacturerEndpointService;
import com.soapdataservice.app.dto.*;
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
public class ManufacturerEndpointImp implements ManufacturerEndpoint {

    private final Logger logger = LoggerFactory.getLogger(ManufacturerEndpointImp.class);
    private static final String NAMESPACE = "http://com/soapdataservice/app/dto";
    private final ManufacturerEndpointService manufacturerEndpointService;

    @Autowired
    public ManufacturerEndpointImp(ManufacturerEndpointService manufacturerEndpointService) {
        this.manufacturerEndpointService = manufacturerEndpointService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getManufacturerByIdRequest")
    @ResponsePayload
    public GetManufacturerByIdResponse getManufacturerById(@RequestPayload GetManufacturerByIdRequest request) {
        GetManufacturerByIdResponse response = new GetManufacturerByIdResponse();

        ManufacturerDto manufacturerDto = manufacturerEndpointService.findById(request.getId());
        response.setManufacturer(manufacturerDto);
        logger.info("IN getManufacturerById - Sending manufacturer with id={}", manufacturerDto.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getManufacturerByNameRequest")
    @ResponsePayload
    public GetManufacturerByNameResponse getManufacturerByName(@RequestPayload GetManufacturerByNameRequest request) {
        GetManufacturerByNameResponse response = new GetManufacturerByNameResponse();

        ManufacturerDto manufacturerDto = manufacturerEndpointService.findByName(request.getName());
        response.setManufacturer(manufacturerDto);
        logger.info("IN getManufacturerByName - Sending manufacturer with name={}", manufacturerDto.getName());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "createManufacturerRequest")
    @ResponsePayload
    public CreateManufacturerResponse createManufacturer(@RequestPayload CreateManufacturerRequest request) {
        CreateManufacturerResponse response = new CreateManufacturerResponse();
        ManufacturerDto manufacturerDto = request.getManufacturer();

        ManufacturerDto createdManufacturer = manufacturerEndpointService.save(manufacturerDto);
        response.setCreatedManufacturer(createdManufacturer);
        logger.info("IN createManufacturer - manufacturer with generated id={} saved", createdManufacturer.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "updateManufacturerRequest")
    @ResponsePayload
    public UpdateManufacturerResponse updateManufacturer(@RequestPayload UpdateManufacturerRequest request) {
        UpdateManufacturerResponse response = new UpdateManufacturerResponse();
        ManufacturerDto manufacturerDto = request.getManufacturer();

        ManufacturerDto updatedManufacturerDto = manufacturerEndpointService.update(manufacturerDto);
        response.setUpdatedManufacturer(updatedManufacturerDto);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "deleteManufacturerByIdRequest")
    @ResponsePayload
    public DeleteManufacturerByIdResponse deleteManufacturerById(@RequestPayload DeleteManufacturerByIdRequest request) {
        DeleteManufacturerByIdResponse response = new DeleteManufacturerByIdResponse();

        response.setAnswer(manufacturerEndpointService.deleteById(request.getId()));
        logger.info("IN deleteManufacturerById - manufacturer with id={} deleted", request.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllManufacturersRequest")
    @ResponsePayload
    public GetAllManufacturersResponse getAllManufacturers(@RequestPayload GetAllManufacturersRequest request) {
        GetAllManufacturersResponse response = new GetAllManufacturersResponse();

        List<ManufacturerDto> manufacturersDto = manufacturerEndpointService.findAll();
        response.getManufacturers().addAll(manufacturersDto);
        logger.info("IN getAllManufacturers - {} manufacturers found and sent", manufacturersDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllManufacturersByCityRequest")
    @ResponsePayload
    @Override
    public GetAllManufacturersByCityResponse getAllManufacturersByCity(@RequestPayload GetAllManufacturersByCityRequest request) {
        GetAllManufacturersByCityResponse response = new GetAllManufacturersByCityResponse();

        List<ManufacturerDto> manufacturersDto = manufacturerEndpointService.findAllByAddressCity(request.getCity());
        response.getManufacturers().addAll(manufacturersDto);
        logger.info("IN getAllManufacturersByCity - {} manufacturers found and sent", manufacturersDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllManufacturersByCountryRequest")
    @ResponsePayload
    @Override
    public GetAllManufacturersByCountryResponse getAllManufacturersByCountry(@RequestPayload GetAllManufacturersByCountryRequest request) {
        GetAllManufacturersByCountryResponse response = new GetAllManufacturersByCountryResponse();

        List<ManufacturerDto> manufacturersDto = manufacturerEndpointService.findAllByAddressCountry(request.getCountry());
        response.getManufacturers().addAll(manufacturersDto);
        logger.info("IN getAllManufacturersByCountry - {} manufacturers found and sent", manufacturersDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllManufacturersByBrandIdRequest")
    @ResponsePayload
    @Override
    public GetAllManufacturersByBrandIdResponse getAllManufacturersByBrandId(@RequestPayload GetAllManufacturersByBrandIdRequest request) {
        GetAllManufacturersByBrandIdResponse response = new GetAllManufacturersByBrandIdResponse();

        List<ManufacturerDto> manufacturersDto = manufacturerEndpointService.findAllByBrandsId(request.getId());
        response.getManufacturers().addAll(manufacturersDto);
        logger.info("IN getAllManufacturersByBrandId - {} manufacturers found and sent", manufacturersDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllManufacturersByBrandNameRequest")
    @ResponsePayload
    @Override
    public GetAllManufacturersByBrandNameResponse getAllManufacturersByBrandName(@RequestPayload GetAllManufacturersByBrandNameRequest request) {
        GetAllManufacturersByBrandNameResponse response = new GetAllManufacturersByBrandNameResponse();

        List<ManufacturerDto> manufacturersDto = manufacturerEndpointService.findAllByBrandsName(request.getName());
        response.getManufacturers().addAll(manufacturersDto);
        logger.info("IN getAllManufacturersByBrandName - {} manufacturers found and sent", manufacturersDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getManufacturerByItemIdRequest")
    @ResponsePayload
    @Override
    public GetManufacturerByItemIdResponse getManufacturerByItemId(@RequestPayload GetManufacturerByItemIdRequest request) {
        GetManufacturerByItemIdResponse response = new GetManufacturerByItemIdResponse();

        ManufacturerDto manufacturerDto = manufacturerEndpointService.findByItemsId(request.getId());
        response.setManufacturer(manufacturerDto);
        logger.info("IN getManufacturerByItemId - Sending manufacturer with id={}", manufacturerDto.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getManufacturerByItemNameRequest")
    @ResponsePayload
    @Override
    public GetManufacturerByItemNameResponse getManufacturerByItemName(@RequestPayload GetManufacturerByItemNameRequest request) {
        GetManufacturerByItemNameResponse response = new GetManufacturerByItemNameResponse();

        ManufacturerDto manufacturerDto = manufacturerEndpointService.findByItemsName(request.getName());
        response.setManufacturer(manufacturerDto);
        logger.info("IN getManufacturerByItemName - Sending manufacturer with name={}", manufacturerDto.getName());
        return response;
    }
}
