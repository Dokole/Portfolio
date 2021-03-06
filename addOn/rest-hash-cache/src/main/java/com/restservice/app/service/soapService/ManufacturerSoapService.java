package com.restservice.app.service.soapService;

import com.restservice.app.domain.cache.redis.ManufacturerCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ManufacturerSoapService {

    ManufacturerCache getManufacturerById(Long id);

    ManufacturerCache getManufacturerByName(String name);

    ManufacturerCache getManufacturerByItemId(Long id);

    ManufacturerCache getManufacturerByItemName(String name);

    List<ManufacturerCache> getAllManufacturers();

    ManufacturerCache createManufacturer(ManufacturerCache manufacturerDto);

    ManufacturerCache updateManufacturer(ManufacturerCache manufacturerDto);

    boolean deleteManufacturerById(Long id);

    List<ManufacturerCache> getManufacturersByAddressCountry(String country);

    List<ManufacturerCache> getManufacturersByAddressCity(String city);

    List<ManufacturerCache> getManufacturersByBrandsName(String name);

    List<ManufacturerCache> getManufacturersByBrandsId(Long id);
}
