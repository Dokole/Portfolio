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
public class BrandSoapClient extends WebServiceGatewaySupport implements BrandSoapRepository {

    private static final Logger logger = LoggerFactory.getLogger(BrandSoapClient.class);

    @Override
    public BrandDto getBrandById(Long id) {
        GetBrandByIdRequest request = new GetBrandByIdRequest();
        request.setId(id);

        GetBrandByIdResponse response = (GetBrandByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        BrandDto brandDto = response.getBrand();
        logger.info("IN getBrandById - brand successfully found by id={}", id);
        return brandDto;
    }

    @Override
    public BrandDto getBrandByName(String name) {
        GetBrandByNameRequest request = new GetBrandByNameRequest();
        request.setName(name);

        GetBrandByNameResponse response = (GetBrandByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        BrandDto brandDto = response.getBrand();
        logger.info("IN getBrandByName - brand successfully found by name={}", brandDto.getName());
        return brandDto;
    }

    @Override
    public List<BrandDto> getAllBrands() {
        GetAllBrandsRequest request = new GetAllBrandsRequest();

        GetAllBrandsResponse response = (GetAllBrandsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<BrandDto> brandsDto = response.getBrands();
        logger.info("IN getAllBrands - {} brands successfully received", brandsDto.size());
        return brandsDto;
    }

    @Override
    public BrandDto createBrand(BrandDto brand) {
        CreateBrandRequest request = new CreateBrandRequest();
        request.setBrand(brand);

        CreateBrandResponse response = (CreateBrandResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));

        BrandDto brandDto = response.getCreatedBrand();
        logger.info("IN createBrand - brand with id={} successfully created", brandDto.getId());
        return brandDto;
    }


    @Override
    public BrandDto updateBrand(BrandDto brand) {
        UpdateBrandRequest request = new UpdateBrandRequest();
        request.setBrand(brand);

        UpdateBrandResponse response = (UpdateBrandResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        BrandDto brandDto = response.getUpdatedBrand();
        logger.info("IN updateBrand - brand with id={} successfully updated", brandDto.getId());
        return brandDto;
    }

    @Override
    public boolean deleteBrandById(Long id) {
        DeleteBrandByIdRequest request = new DeleteBrandByIdRequest();
        request.setId(id);

        DeleteBrandByIdResponse response = (DeleteBrandByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        boolean answer = response.isAnswer();
        logger.info("IN deleteBrandById - brand with id={} successfully deleted", id);
        return answer;
    }

    @Override
    public List<BrandDto> getBrandsByManufacturersId(Long id) {
        GetAllBrandsByManufacturerIdRequest request = new GetAllBrandsByManufacturerIdRequest();
        request.setId(id);
        GetAllBrandsByManufacturerIdResponse response = (GetAllBrandsByManufacturerIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<BrandDto> brandsDto = response.getBrands();
        logger.info("IN getBrandsByManufacturersId - {} brands successfully received", brandsDto.size());
        return brandsDto;
    }

    @Override
    public List<BrandDto> getBrandsByManufacturersName(String name) {
        GetAllBrandsByManufacturerNameRequest request = new GetAllBrandsByManufacturerNameRequest();
        request.setName(name);
        GetAllBrandsByManufacturerNameResponse response = (GetAllBrandsByManufacturerNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<BrandDto> brandsDto = response.getBrands();
        logger.info("IN getBrandsByManufacturersName - {} brands successfully received", brandsDto.size());
        return brandsDto;
    }

    @Override
    public List<BrandDto> getBrandsByAddressCountry(String country) {
        GetAllBrandsByCountryRequest request = new GetAllBrandsByCountryRequest();
        request.setCountry(country);
        GetAllBrandsByCountryResponse response = (GetAllBrandsByCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<BrandDto> brandsDto = response.getBrands();
        logger.info("IN getBrandsByAddressCountry - {} brands successfully received", brandsDto.size());
        return brandsDto;
    }

    @Override
    public List<BrandDto> getBrandsByAddressCity(String city) {
        GetAllBrandsByCityRequest request = new GetAllBrandsByCityRequest();
        request.setCity(city);
        GetAllBrandsByCityResponse response = (GetAllBrandsByCityResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        List<BrandDto> brandsDto = response.getBrands();
        logger.info("IN getBrandsByAddressCity - {} brands successfully received", brandsDto.size());
        return brandsDto;
    }

    @Override
    public BrandDto getBrandByItemId(Long id) {
        GetBrandByItemIdRequest request = new GetBrandByItemIdRequest();
        request.setId(id);
        GetBrandByItemIdResponse response = (GetBrandByItemIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        BrandDto brandDto = response.getBrand();
        logger.info("IN getBrandByItemId - brands with id={} successfully received", brandDto.getId());
        return brandDto;
    }

    @Override
    public BrandDto getBrandByItemName(String name) {
        GetBrandByItemNameRequest request = new GetBrandByItemNameRequest();
        request.setName(name);
        GetBrandByItemNameResponse response = (GetBrandByItemNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ClientVars.SOAP_SERVICE_URI.getValue(), request,
                        new SoapActionCallback(ClientVars.SOAP_ACTION.getValue()));
        BrandDto brandDto = response.getBrand();
        logger.info("IN getBrandByItemName - brands with id={} successfully received", brandDto.getId());
        return brandDto;
    }
}
