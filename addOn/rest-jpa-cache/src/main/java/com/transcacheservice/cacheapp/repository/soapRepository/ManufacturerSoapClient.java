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
public class ManufacturerSoapClient extends WebServiceGatewaySupport implements ManufacturerSoapRepository {

    private static final Logger logger = LoggerFactory.getLogger(ManufacturerSoapClient.class);

    @Override
    public ManufacturerDto getManufacturerById(Long id) {
        GetManufacturerByIdRequest request = new GetManufacturerByIdRequest();
        request.setId(id);

        GetManufacturerByIdResponse response = (GetManufacturerByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ManufacturerDto manufacturerDto = response.getManufacturer();
        logger.info("IN getManufacturerById - manufacturer successfully found by id={}", id);
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto getManufacturerByName(String name) {
        GetManufacturerByNameRequest request = new GetManufacturerByNameRequest();
        request.setName(name);

        GetManufacturerByNameResponse response = (GetManufacturerByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ManufacturerDto manufacturerDto = response.getManufacturer();
        logger.info("IN getManufacturerByName - manufacturer successfully found by name={}", manufacturerDto.getName());
        return manufacturerDto;
    }

    @Override
    public List<ManufacturerDto> getAllManufacturers() {
        GetAllManufacturersRequest request = new GetAllManufacturersRequest();

        GetAllManufacturersResponse response = (GetAllManufacturersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ManufacturerDto> manufacturerDto = response.getManufacturers();
        logger.info("IN getAllManufacturers - {} manufacturers successfully received", manufacturerDto.size());
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto createManufacturer(ManufacturerDto manufacturer) {
        CreateManufacturerRequest request = new CreateManufacturerRequest();
        request.setManufacturer(manufacturer);

        CreateManufacturerResponse response = (CreateManufacturerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ManufacturerDto manufacturerDto = response.getCreatedManufacturer();
        logger.info("IN createManufacturer - manufacturer with id={} successfully created", manufacturerDto.getId());
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto updateManufacturer(ManufacturerDto manufacturer) {
        UpdateManufacturerRequest request = new UpdateManufacturerRequest();
        request.setManufacturer(manufacturer);

        UpdateManufacturerResponse response = (UpdateManufacturerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ManufacturerDto manufacturerDto = response.getUpdatedManufacturer();
        logger.info("IN updateBrand - manufacturer with id={} successfully updated", manufacturerDto.getId());
        return manufacturerDto;
    }

    @Override
    public boolean deleteManufacturerById(Long id) {
        DeleteManufacturerByIdRequest request = new DeleteManufacturerByIdRequest();
        request.setId(id);

        DeleteManufacturerByIdResponse response = (DeleteManufacturerByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        boolean answer = response.isAnswer();
        logger.info("IN deleteManufacturerById - manufacturer with id={} successfully deleted", id);
        return answer;
    }

    @Override
    public List<ManufacturerDto> getManufacturersByAddressCountry(String country) {
        GetAllManufacturersByCountryRequest request = new GetAllManufacturersByCountryRequest();
        request.setCountry(country);

        GetAllManufacturersByCountryResponse response = (GetAllManufacturersByCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ManufacturerDto> manufacturerDto = response.getManufacturers();
        logger.info("IN getManufacturersByAddressCountry - {} manufacturers successfully received", manufacturerDto.size());
        return manufacturerDto;
    }

    @Override
    public List<ManufacturerDto> getManufacturersByAddressCity(String city) {
        GetAllManufacturersByCityRequest request = new GetAllManufacturersByCityRequest();
        request.setCity(city);

        GetAllManufacturersByCityResponse response = (GetAllManufacturersByCityResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ManufacturerDto> manufacturerDto = response.getManufacturers();
        logger.info("IN getManufacturersByAddressCity - {} manufacturers successfully received", manufacturerDto.size());
        return manufacturerDto;
    }

    @Override
    public List<ManufacturerDto> getManufacturersByBrandsName(String name) {
        GetAllManufacturersByBrandNameRequest request = new GetAllManufacturersByBrandNameRequest();
        request.setName(name);

        GetAllManufacturersByBrandNameResponse response = (GetAllManufacturersByBrandNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ManufacturerDto> manufacturerDto = response.getManufacturers();
        logger.info("IN getManufacturersByBrandsName - {} manufacturers successfully received", manufacturerDto.size());
        return manufacturerDto;
    }

    @Override
    public List<ManufacturerDto> getManufacturersByBrandsId(Long id) {
        GetAllManufacturersByBrandIdRequest request = new GetAllManufacturersByBrandIdRequest();
        request.setId(id);

        GetAllManufacturersByBrandIdResponse response = (GetAllManufacturersByBrandIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<ManufacturerDto> manufacturerDto = response.getManufacturers();
        logger.info("IN getManufacturersByBrandsId - {} manufacturers successfully received", manufacturerDto.size());
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto getManufacturerByItemId(Long id) {
        GetManufacturerByItemIdRequest request = new GetManufacturerByItemIdRequest();
        request.setId(id);

        GetManufacturerByItemIdResponse response = (GetManufacturerByItemIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ManufacturerDto manufacturerDto = response.getManufacturer();
        logger.info("IN getManufacturerByItemId - manufacturer with id={} successfully received", manufacturerDto.getId());
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto getManufacturerByItemName(String name) {
        GetManufacturerByItemNameRequest request = new GetManufacturerByItemNameRequest();
        request.setName(name);

        GetManufacturerByItemNameResponse response = (GetManufacturerByItemNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        ManufacturerDto manufacturerDto = response.getManufacturer();
        logger.info("IN getManufacturerByItemName - manufacturer with id={} successfully received", manufacturerDto.getId());
        return manufacturerDto;
    }
}
