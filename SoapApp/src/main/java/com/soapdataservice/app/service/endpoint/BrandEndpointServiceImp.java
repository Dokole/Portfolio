package com.soapdataservice.app.service.endpoint;

import com.soapdataservice.app.domain.Brand;
import com.soapdataservice.app.exceptions.BadRequestException;
import com.soapdataservice.app.exceptions.NotFoundException;
import com.soapdataservice.app.service.data.BrandDataService;
import com.soapdataservice.app.util.CastToDtoUtil;
import com.soapdataservice.app.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("brandEndpointService")
public class BrandEndpointServiceImp implements BrandEndpointService {
    private final Logger logger = LoggerFactory.getLogger(BrandEndpointServiceImp.class);

    private final BrandDataService brandDataService;
    private final CastToDtoUtil castToDtoUtil;
    @Autowired
    public BrandEndpointServiceImp(BrandDataService brandDataService, CastToDtoUtil castToDtoUtil) {
        this.brandDataService = brandDataService;
        this.castToDtoUtil = castToDtoUtil;
    }

    @Override
    public List<BrandDto> findAll() {
        Set<Brand> brands;
        List<BrandDto> brandsDto = new LinkedList<>();
        brands = brandDataService.findAll();
        if (brands.isEmpty()) {
            logger.error("IN findAll - No brands in the database");
            throw new NotFoundException("No brands found in the database.");
        }
        brands.forEach(i -> brandsDto.add(castToDtoUtil.brandToBrandDto(i)));
        return brandsDto;
    }

    @Override
    public BrandDto findByName(String name) {
        Brand brand = brandDataService.findByName(name);
        if (brand == null) {
            logger.warn("IN findByName - no brand found by name={}", name);
            throw new NotFoundException("No brand found by name=" + name);
        }
        return castToDtoUtil.brandToBrandDto(brand);
    }

    @Override
    public BrandDto findById(Long id) {
        Brand brand = brandDataService.findById(id);
        if (brand == null) {
            logger.warn("IN findById - no brand found by id={}", id);
            throw new NotFoundException("No brand found by id=" + id);
        }
        return castToDtoUtil.brandToBrandDto(brand);
    }

    @Override
    public BrandDto save(BrandDto brandDto) {
        if (brandDto.getId() != null) {
            throw new BadRequestException("Id=" + brandDto.getId() + " should be null to create a brand. Can't be saved.");
        }
        if (brandDataService.existsByName(brandDto.getName())) {
            throw new BadRequestException("Brand with name=" + brandDto.getName() + " already exists");
        }
        Brand brand = castToDtoUtil.brandDtoToBrand(brandDto);
        brand = brandDataService.save(brand);
        brandDto = castToDtoUtil.brandToBrandDto(brand);
        System.out.println("AFTER CAST");
        return brandDto;
    }

    @Override
    public BrandDto update(BrandDto brandDto) {
        if (!existsById(brandDto.getId())) {
            throw new BadRequestException("Can't update brand with id=" + brandDto.getId() + ". It doesn't exists");
        }
        Brand brand = castToDtoUtil.brandDtoToBrand(brandDto);
        brand = brandDataService.save(brand);
        brandDto = castToDtoUtil.brandToBrandDto(brand);
        return brandDto;
    }

    @Override
    public boolean deleteById(Long id) {
        brandDataService.deleteById(id);
        return true;
    }

    @Override
    public boolean existsById(Long id) {
        return brandDataService.existsById(id);
    }

    @Override
    public BrandDto findByItemsId(Long id) {
        Brand brand = brandDataService.findByItemsId(id);
        if (brand == null) {
            logger.warn("IN findById - no brand found by item id={}", id);
            throw new NotFoundException("No brand found by item id=" + id);
        }
        return castToDtoUtil.brandToBrandDto(brand);
    }

    @Override
    public BrandDto findByItemsName(String name) {
        Brand brand = brandDataService.findByItemsName(name);
        if (brand == null) {
            logger.warn("IN findByName - no brand found by item name={}", name);
            throw new NotFoundException("No brand found by item name=" + name);
        }
        return castToDtoUtil.brandToBrandDto(brand);
    }

    @Override
    public List<BrandDto> findAllByManufacturersId(Long id) {
        Set<Brand> brands;
        List<BrandDto> brandsDto = new LinkedList<>();
        brands = brandDataService.findAllByManufacturersId(id);
        if (brands.isEmpty()) {
            logger.error("IN findAllByManufacturersId - no brands found in the database by manufacturer id={}", id);
            throw new NotFoundException("No brands found in the database by manufacturer id=" + id);
        }
        brands.forEach(i -> brandsDto.add(castToDtoUtil.brandToBrandDto(i)));
        return brandsDto;
    }

    @Override
    public List<BrandDto> findAllByManufacturersName(String name) {
        Set<Brand> brands;
        List<BrandDto> brandsDto = new LinkedList<>();
        brands = brandDataService.findAllByManufacturersName(name);
        if (brands.isEmpty()) {
            logger.error("IN findAllByManufacturersName - no brands found in the database by manufacturer name={}", name);
            throw new NotFoundException("No brands found in the database by manufacturer name=" + name);
        }
        brands.forEach(i -> brandsDto.add(castToDtoUtil.brandToBrandDto(i)));
        return brandsDto;
    }

    @Override
    public List<BrandDto> findAllByAddressCountry(String country) {
        Set<Brand> brands;
        List<BrandDto> brandsDto = new LinkedList<>();
        brands = brandDataService.findAllByAddressCountry(country);
        if (brands.isEmpty()) {
            logger.error("IN findAllByAddressCountry - no brands found in the database by country={}", country);
            throw new NotFoundException("No brands found in the database by country=" + country);
        }
        brands.forEach(i -> brandsDto.add(castToDtoUtil.brandToBrandDto(i)));
        return brandsDto;
    }

    @Override
    public List<BrandDto> findAllByAddressCity(String city) {
        Set<Brand> brands;
        List<BrandDto> brandsDto = new LinkedList<>();
        brands = brandDataService.findAllByAddressCity(city);
        if (brands.isEmpty()) {
            logger.error("IN findAllByAddressCity - no brands found in the database by city={}", city);
            throw new NotFoundException("No brands found in the database by city=" + city);
        }
        brands.forEach(i -> brandsDto.add(castToDtoUtil.brandToBrandDto(i)));
        return brandsDto;
    }
}
