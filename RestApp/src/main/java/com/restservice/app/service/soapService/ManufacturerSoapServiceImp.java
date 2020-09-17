package com.restservice.app.service.soapService;

import com.restservice.app.domain.cache.redis.ManufacturerCache;
import com.restservice.app.repository.soapRepository.ManufacturerSoapRepository;
import com.restservice.app.dto.database.ManufacturerDto;
import com.restservice.app.util.CastDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ManufacturerSoapServiceImp implements ManufacturerSoapService {

    private final Logger logger = LoggerFactory.getLogger(ManufacturerSoapServiceImp.class);

    private final String cacheName = "Manufacturer";

    private final ManufacturerSoapRepository manufacturerSoapRepository;
    private final CastDto castDto;

    @Autowired
    public ManufacturerSoapServiceImp(ManufacturerSoapRepository manufacturerSoapRepository, CastDto castDto) {
        this.manufacturerSoapRepository = manufacturerSoapRepository;
        this.castDto = castDto;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#id)")
    @Override
    public ManufacturerCache getManufacturerById(Long id) {
        return castDto.manufacturerFromManufacturerDto(manufacturerSoapRepository.getManufacturerById(id));
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#name)")
    @Override
    public ManufacturerCache getManufacturerByName(String name) {
        return castDto.manufacturerFromManufacturerDto(manufacturerSoapRepository.getManufacturerByName(name));
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#id)")
    @Override
    public ManufacturerCache getManufacturerByItemId(Long id) {
        return castDto.manufacturerFromManufacturerDto(manufacturerSoapRepository.getManufacturerByItemId(id));
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#name)")
    @Override
    public ManufacturerCache getManufacturerByItemName(String name) {
        return castDto.manufacturerFromManufacturerDto(manufacturerSoapRepository.getManufacturerByItemName(name));
    }

    @Override
    public List<ManufacturerCache> getAllManufacturers() {
        List<ManufacturerDto> manufacturersDto = manufacturerSoapRepository.getAllManufacturers();
        List<ManufacturerCache> manufacturers = new LinkedList<>();
        manufacturersDto.forEach(m -> manufacturers.add(castDto.manufacturerFromManufacturerDto(m)));
        logger.info("IN getAllManufacturers - {} manufacturers received", manufacturers.size());
        return manufacturers;
    }

    @CacheEvict(cacheNames = {"Brand", "Item", "Manufacturer"})
    @Override
    public ManufacturerCache createManufacturer(ManufacturerCache manufacturer) {
        ManufacturerDto manufacturerDto = castDto.manufacturerDtoFromManufacturer(manufacturer);
        manufacturerDto = manufacturerSoapRepository.createManufacturer(manufacturerDto);
        return castDto.manufacturerFromManufacturerDto(manufacturerDto);
    }

    @CacheEvict(cacheNames = {"Brand", "Item", "Manufacturer"})
    @Override
    public ManufacturerCache updateManufacturer(ManufacturerCache manufacturer) {
        ManufacturerDto manufacturerDto = castDto.manufacturerDtoFromManufacturer(manufacturer);
        manufacturerDto = manufacturerSoapRepository.updateManufacturer(manufacturerDto);
        return castDto.manufacturerFromManufacturerDto(manufacturerDto);
    }

    @CacheEvict(cacheNames = {"Brand", "Item", "Manufacturer"})
    @Override
    public boolean deleteManufacturerById(Long id) {
        return manufacturerSoapRepository.deleteManufacturerById(id);
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#country)")
    @Override
    public List<ManufacturerCache> getManufacturersByAddressCountry(String country) {
        List<ManufacturerDto> manufacturersDto = manufacturerSoapRepository.getManufacturersByAddressCountry(country);
        List<ManufacturerCache> manufacturers = new LinkedList<>();
        manufacturersDto.forEach(m -> manufacturers.add(castDto.manufacturerFromManufacturerDto(m)));
        logger.info("IN getManufacturersByAddressCountry - {} manufacturers received", manufacturers.size());
        return manufacturers;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#city)")
    @Override
    public List<ManufacturerCache> getManufacturersByAddressCity(String city) {
        List<ManufacturerDto> manufacturersDto = manufacturerSoapRepository.getManufacturersByAddressCity(city);
        List<ManufacturerCache> manufacturers = new LinkedList<>();
        manufacturersDto.forEach(m -> manufacturers.add(castDto.manufacturerFromManufacturerDto(m)));
        logger.info("IN getManufacturersByAddressCity - {} manufacturers received", manufacturers.size());
        return manufacturers;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#name)")
    @Override
    public List<ManufacturerCache> getManufacturersByBrandsName(String name) {
        List<ManufacturerDto> manufacturersDto = manufacturerSoapRepository.getManufacturersByBrandsName(name);
        List<ManufacturerCache> manufacturers = new LinkedList<>();
        manufacturersDto.forEach(m -> manufacturers.add(castDto.manufacturerFromManufacturerDto(m)));
        logger.info("IN getManufacturersByBrandsName - {} manufacturers received", manufacturers.size());
        return manufacturers;
    }

    @Cacheable(value = cacheName, key = "@cacheIdNamer.nameOfExecutableMethod.concat(#id)")
    @Override
    public List<ManufacturerCache> getManufacturersByBrandsId(Long id) {
        List<ManufacturerDto> manufacturersDto = manufacturerSoapRepository.getManufacturersByBrandsId(id);
        List<ManufacturerCache> manufacturers = new LinkedList<>();
        manufacturersDto.forEach(m -> manufacturers.add(castDto.manufacturerFromManufacturerDto(m)));
        logger.info("IN getManufacturersByBrandsId - {} manufacturers received", manufacturers.size());
        return manufacturers;
    }
}
