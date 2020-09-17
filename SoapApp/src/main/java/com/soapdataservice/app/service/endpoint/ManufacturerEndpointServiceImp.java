package com.soapdataservice.app.service.endpoint;

import com.soapdataservice.app.domain.Manufacturer;
import com.soapdataservice.app.exceptions.BadRequestException;
import com.soapdataservice.app.exceptions.NotFoundException;
import com.soapdataservice.app.service.data.ManufacturerDataService;
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

@Service("manufacturerEndpointService")
public class ManufacturerEndpointServiceImp implements ManufacturerEndpointService {

    private final Logger logger = LoggerFactory.getLogger(ManufacturerEndpointServiceImp.class);

    private final ManufacturerDataService manufacturerDataService;
    private final CastToDtoUtil castToDtoUtil;

    @Autowired
    public ManufacturerEndpointServiceImp(ManufacturerDataService manufacturerDataService, CastToDtoUtil castToDtoUtil) {
        this.manufacturerDataService = manufacturerDataService;
        this.castToDtoUtil = castToDtoUtil;
    }

    @Override
    public List<ManufacturerDto> findAll() {
        Set<Manufacturer> manufacturers = manufacturerDataService.findAll();
        List<ManufacturerDto> manufacturersDto = new LinkedList<>();

        if (manufacturers.isEmpty()) {
            logger.error("IN findAll - No manufacturers in the database");
            throw new NotFoundException("No manufacturers found in the database.");
        }
        manufacturers.forEach(i -> manufacturersDto.add(castToDtoUtil.manufacturerToManufacturerDto(i)));
        return manufacturersDto;
    }

    @Override
    public ManufacturerDto findById(Long id) {
        Manufacturer manufacturer = manufacturerDataService.findById(id);

        if (manufacturer == null) {
            logger.warn("IN findById - no manufacturer found by id={}", id);
            throw new NotFoundException("No manufacturer found by id=" + id);
        }
        return castToDtoUtil.manufacturerToManufacturerDto(manufacturer);
    }

    @Override
    public ManufacturerDto findByName(String name) {
        Manufacturer manufacturer = manufacturerDataService.findByName(name);

        if (manufacturer == null) {
            logger.warn("IN findByName - no manufacturer found by name={}", name);
            throw new NotFoundException("No manufacturer found by name=" + name);
        }
        return castToDtoUtil.manufacturerToManufacturerDto(manufacturer);
    }

    @Override
    public boolean deleteById(Long id) {
        manufacturerDataService.deleteById(id);
        return true;
    }

    @Override
    public ManufacturerDto save(ManufacturerDto manufacturerDto) {
        if (manufacturerDto.getId() != null) {
            throw new BadRequestException("Id=" + manufacturerDto.getId() +
                    " should be null to create a manufacturer. Can't be saved.");
        }
        if (manufacturerDataService.existsByName(manufacturerDto.getName())) {
            throw new BadRequestException("Manufacturer with name=" + manufacturerDto.getName() + " already exists");
        }
        Manufacturer manufacturer = castToDtoUtil.manufacturerDtoToManufacturer(manufacturerDto);
        manufacturer = manufacturerDataService.save(manufacturer);

        manufacturerDto = castToDtoUtil.manufacturerToManufacturerDto(manufacturer);
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto update(ManufacturerDto manufacturerDto) {
        if (!existsById(manufacturerDto.getId())) {
            throw new BadRequestException("Can't update manufacturer with id="
                    + manufacturerDto.getId() + ". It doesn't exists");
        }
        Manufacturer manufacturer = castToDtoUtil.manufacturerDtoToManufacturer(manufacturerDto);
        manufacturer = manufacturerDataService.save(manufacturer);

        manufacturerDto = castToDtoUtil.manufacturerToManufacturerDto(manufacturer);
        return manufacturerDto;
    }

    @Override
    public boolean existsById(Long id) {
        return manufacturerDataService.existsById(id);
    }

    @Override
    public ManufacturerDto findByItemsId(Long id) {
        Manufacturer manufacturer = manufacturerDataService.findByItemsId(id);

        if (manufacturer == null) {
            logger.warn("IN findByItemsId - no manufacturer found by item id={}", id);
            throw new NotFoundException("No manufacturer found by item id=" + id);
        }
        return castToDtoUtil.manufacturerToManufacturerDto(manufacturer);
    }

    @Override
    public ManufacturerDto findByItemsName(String name) {
        Manufacturer manufacturer = manufacturerDataService.findByItemsName(name);

        if (manufacturer == null) {
            logger.warn("IN findByItemsName - no manufacturer found by item name={}", name);
            throw new NotFoundException("No manufacturer found by item name=" + name);
        }
        return castToDtoUtil.manufacturerToManufacturerDto(manufacturer);
    }

    @Override
    public List<ManufacturerDto> findAllByAddressCountry(String country) {
        Set<Manufacturer> manufacturers = manufacturerDataService.findAllByAddressCountry(country);
        List<ManufacturerDto> manufacturersDto = new LinkedList<>();

        if (manufacturers.isEmpty()) {
            logger.error("IN findAll - No manufacturers found in the database by country={}", country);
            throw new NotFoundException("No manufacturers found in the database by country=" + country);
        }
        manufacturers.forEach(i -> manufacturersDto.add(castToDtoUtil.manufacturerToManufacturerDto(i)));
        return manufacturersDto;
    }

    @Override
    public List<ManufacturerDto> findAllByAddressCity(String city) {
        Set<Manufacturer> manufacturers = manufacturerDataService.findAllByAddressCity(city);
        List<ManufacturerDto> manufacturersDto = new LinkedList<>();

        if (manufacturers.isEmpty()) {
            logger.error("IN findAll - No manufacturers found in the database by city={}", city);
            throw new NotFoundException("No manufacturers found in the database by city=" + city);
        }
        manufacturers.forEach(i -> manufacturersDto.add(castToDtoUtil.manufacturerToManufacturerDto(i)));
        return manufacturersDto;
    }

    @Override
    public List<ManufacturerDto> findAllByBrandsName(String name) {
        Set<Manufacturer> manufacturers = manufacturerDataService.findAllByBrandsName(name);
        List<ManufacturerDto> manufacturersDto = new LinkedList<>();

        if (manufacturers.isEmpty()) {
            logger.error("IN findAll - No manufacturers found in the database by brand name={}", name);
            throw new NotFoundException("No manufacturers found in the database by brand name=" + name);
        }
        manufacturers.forEach(i -> manufacturersDto.add(castToDtoUtil.manufacturerToManufacturerDto(i)));
        return manufacturersDto;
    }

    @Override
    public List<ManufacturerDto> findAllByBrandsId(Long id) {
        Set<Manufacturer> manufacturers = manufacturerDataService.findAllByBrandsId(id);
        List<ManufacturerDto> manufacturersDto = new LinkedList<>();

        if (manufacturers.isEmpty()) {
            logger.error("IN findAll - No manufacturers found in the database by brand id={}", id);
            throw new NotFoundException("No manufacturers found in the database by brand id=" + id);
        }
        manufacturers.forEach(i -> manufacturersDto.add(castToDtoUtil.manufacturerToManufacturerDto(i)));
        return manufacturersDto;
    }
}
