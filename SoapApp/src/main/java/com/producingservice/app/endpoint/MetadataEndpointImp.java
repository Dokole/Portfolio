package com.producingservice.app.endpoint;


import com.producingservice.app.service.endpoint.MetadataEndpointService;
import database.dto.app.producingservice.com.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Endpoint
public class MetadataEndpointImp implements MetadataEndpoint {

    private final Logger logger = LoggerFactory.getLogger(MetadataEndpointImp.class);
    private final MetadataEndpointService metadataEndpointService;
    private static final String NAMESPACE = "http://com.producingservice.app.dto.database";

    @Autowired
    public MetadataEndpointImp(MetadataEndpointService metadataEndpointService) {
        this.metadataEndpointService = metadataEndpointService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getItemCreationDataRequest")
    @ResponsePayload
    public GetCreationDataResponse getItemCreation(@RequestPayload GetItemCreationDataRequest request) {
        GetCreationDataResponse response = new GetCreationDataResponse();
        response.setMetadata(metadataEndpointService.getForItemCreation());
        logger.info("IN getMetadata - Metadata for items successfully found");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBrandCreationDataRequest")
    @ResponsePayload
    public GetCreationDataResponse getItemCreation(@RequestPayload GetBrandCreationDataRequest request) {
        GetCreationDataResponse response = new GetCreationDataResponse();
        response.setMetadata(metadataEndpointService.getForBrandCreation());
        logger.info("IN getMetadata - Metadata for brands successfully found");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getManufacturerCreationDataRequest")
    @ResponsePayload
    public GetCreationDataResponse getItemCreation(@RequestPayload GetManufacturerCreationDataRequest request) {
        GetCreationDataResponse response = new GetCreationDataResponse();
        response.setMetadata(metadataEndpointService.getForManufacturerCreation());
        logger.info("IN getMetadata - Metadata for manufacturers successfully found");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getCategoryCreationDataRequest")
    @ResponsePayload
    public GetCreationDataResponse getItemCreation(@RequestPayload GetCategoryCreationDataRequest request) {
        GetCreationDataResponse response = new GetCreationDataResponse();
        response.setMetadata(metadataEndpointService.getForCategoryCreation());
        logger.info("IN getMetadata - Metadata for categories successfully found");
        return response;
    }
}
