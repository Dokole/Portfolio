package com.transcacheservice.cacheapp.repository.soapRepository;

import com.transcacheservice.cacheapp.dto.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Repository
public class ObjectCreationDataClient extends WebServiceGatewaySupport implements ObjectCreationDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(ObjectCreationDataClient.class);

    @Override
    public CreateDataDto getItemCreationData() {
        GetItemCreationDataRequest request = new GetItemCreationDataRequest();

        GetCreationDataResponse response = (GetCreationDataResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CreateDataDto metadataDto = response.getMetadata();
        logger.info("IN getItemCreationData - metadata successfully received");
        return metadataDto;
    }

    @Override
    public CreateDataDto getBrandCreationData() {
        GetBrandCreationDataRequest request = new GetBrandCreationDataRequest();

        GetCreationDataResponse response = (GetCreationDataResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CreateDataDto metadataDto = response.getMetadata();
        logger.info("IN getBrandCreationData - metadata successfully received");
        return metadataDto;
    }

    @Override
    public CreateDataDto getManufacturerCreationData() {
        GetManufacturerCreationDataRequest request = new GetManufacturerCreationDataRequest();

        GetCreationDataResponse response = (GetCreationDataResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CreateDataDto metadataDto = response.getMetadata();
        logger.info("IN getManufacturerCreationData - metadata successfully received");
        return metadataDto;
    }

    @Override
    public CreateDataDto getCategoryCreationData() {
        GetCategoryCreationDataRequest request = new GetCategoryCreationDataRequest();

        GetCreationDataResponse response = (GetCreationDataResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        CreateDataDto metadataDto = response.getMetadata();
        logger.info("IN getCategoryCreationData - metadata successfully received");
        return metadataDto;
    }
}
