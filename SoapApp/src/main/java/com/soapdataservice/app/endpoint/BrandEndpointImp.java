package com.soapdataservice.app.endpoint;

import com.soapdataservice.app.service.endpoint.BrandEndpointService;
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
public class BrandEndpointImp implements BrandEndpoint {

    private final Logger logger = LoggerFactory.getLogger(BrandEndpointImp.class);
    private static final String NAMESPACE = "http://com/soapdataservice/app/dto";

    private final BrandEndpointService brandEndpointService;

    @Autowired
    public BrandEndpointImp(BrandEndpointService brandEndpointService) {
        this.brandEndpointService = brandEndpointService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBrandByIdRequest")
    @ResponsePayload
    public GetBrandByIdResponse getBrandById(@RequestPayload GetBrandByIdRequest request) {
        GetBrandByIdResponse response = new GetBrandByIdResponse();

        BrandDto brandDto = brandEndpointService.findById(request.getId());
        response.setBrand(brandDto);
        logger.info("IN getBrandById - Sending brand with id={}", brandDto.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBrandByNameRequest")
    @ResponsePayload
    public GetBrandByNameResponse getBrandByName(@RequestPayload GetBrandByNameRequest request) {
        GetBrandByNameResponse response = new GetBrandByNameResponse();

        BrandDto brandDto = brandEndpointService.findByName(request.getName());
        response.setBrand(brandDto);
        logger.info("IN getBrandByName - Sending brand with name={}", brandDto.getName());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "createBrandRequest")
    @ResponsePayload
    public CreateBrandResponse createBrand(@RequestPayload CreateBrandRequest request) {
        CreateBrandResponse response = new CreateBrandResponse();
        BrandDto brandDto = request.getBrand();

        BrandDto createdBrand = brandEndpointService.save(brandDto);
        response.setCreatedBrand(createdBrand);
        logger.info("IN createBrand - Brand with generated id={} saved", createdBrand.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "updateBrandRequest")
    @ResponsePayload
    public UpdateBrandResponse updateBrand(@RequestPayload UpdateBrandRequest request) {
        UpdateBrandResponse response = new UpdateBrandResponse();
        BrandDto brandDto = request.getBrand();

        BrandDto updatedBrandDto = brandEndpointService.update(brandDto);
        response.setUpdatedBrand(updatedBrandDto);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "deleteBrandByIdRequest")
    @ResponsePayload
    public DeleteBrandByIdResponse deleteBrandById(@RequestPayload DeleteBrandByIdRequest request) {
        DeleteBrandByIdResponse response = new DeleteBrandByIdResponse();

        response.setAnswer(brandEndpointService.deleteById(request.getId()));
        logger.info("IN deleteBrandById - brand with id={} deleted", request.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllBrandsRequest")
    @ResponsePayload
    public GetAllBrandsResponse getAllBrands(@RequestPayload GetAllBrandsRequest request) {
        GetAllBrandsResponse response = new GetAllBrandsResponse();
        List<BrandDto> brandsDto = brandEndpointService.findAll();
        response.getBrands().addAll(brandsDto);
        logger.info("IN getAllBrands - {} brands found and sent", brandsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllBrandsByCityRequest")
    @ResponsePayload
    @Override
    public GetAllBrandsByCityResponse getAllBrandsByCity(@RequestPayload GetAllBrandsByCityRequest request) {
        GetAllBrandsByCityResponse response = new GetAllBrandsByCityResponse();
        List<BrandDto> brandsDto = brandEndpointService.findAllByAddressCity(request.getCity());
        response.getBrands().addAll(brandsDto);
        logger.info("IN getAllBrandsByCity - {} brands found and sent", brandsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllBrandsByCountryRequest")
    @ResponsePayload
    @Override
    public GetAllBrandsByCountryResponse getAllBrandsByCountry(@RequestPayload GetAllBrandsByCountryRequest request) {
        GetAllBrandsByCountryResponse response = new GetAllBrandsByCountryResponse();
        List<BrandDto> brandsDto = brandEndpointService.findAllByAddressCountry(request.getCountry());
        response.getBrands().addAll(brandsDto);
        logger.info("IN getAllBrandsByCountry - {} brands found and sent", brandsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllBrandsByManufacturerIdRequest")
    @ResponsePayload
    @Override
    public GetAllBrandsByManufacturerIdResponse getAllBrandsByManufacturerId(@RequestPayload GetAllBrandsByManufacturerIdRequest request) {
        GetAllBrandsByManufacturerIdResponse response = new GetAllBrandsByManufacturerIdResponse();
        List<BrandDto> brandsDto = brandEndpointService.findAllByManufacturersId(request.getId());
        response.getBrands().addAll(brandsDto);
        logger.info("IN getAllBrandsByManufacturerId - {} brands found and sent", brandsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getAllBrandsByManufacturerNameRequest")
    @ResponsePayload
    @Override
    public GetAllBrandsByManufacturerNameResponse getAllBrandsByManufacturerName(@RequestPayload GetAllBrandsByManufacturerNameRequest request) {
        GetAllBrandsByManufacturerNameResponse response = new GetAllBrandsByManufacturerNameResponse();
        List<BrandDto> brandsDto = brandEndpointService.findAllByManufacturersName(request.getName());
        response.getBrands().addAll(brandsDto);
        logger.info("IN getAllBrandsByManufacturerName - {} brands found and sent", brandsDto.size());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBrandByItemIdRequest")
    @ResponsePayload
    @Override
    public GetBrandByItemIdResponse getBrandByItemId(@RequestPayload GetBrandByItemIdRequest request) {
        GetBrandByItemIdResponse response = new GetBrandByItemIdResponse();

        BrandDto brandDto = brandEndpointService.findByItemsId(request.getId());
        response.setBrand(brandDto);
        logger.info("IN getBrandByItemId - Sending brand with id={}", brandDto.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBrandByItemNameRequest")
    @ResponsePayload
    @Override
    public GetBrandByItemNameResponse getBrandByItemName(@RequestPayload GetBrandByItemNameRequest request) {
        GetBrandByItemNameResponse response = new GetBrandByItemNameResponse();

        BrandDto brandDto = brandEndpointService.findByItemsName(request.getName());
        response.setBrand(brandDto);
        logger.info("IN getBrandByItemName - Sending brand with name={}", brandDto.getName());
        return response;
    }
}
