package com.transcacheservice.cacheapp.service.databaseService;

import com.transcacheservice.cacheapp.domain.cache.redis.ManufacturerCache;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ManufacturerDatabaseService {

    ManufacturerCache getManufacturerById(String id);

    ManufacturerCache getManufacturerByName(String name);

    ManufacturerCache getManufacturerByItemId(String id);

    ManufacturerCache getManufacturerByItemName(String name);

    List<ManufacturerCache> getAllManufacturersByAddressCity(String city);

    List<ManufacturerCache> getAllManufacturersByAddressCountry(String country);

    List<ManufacturerCache> getAllManufacturersByBrandsId(String id);

    List<ManufacturerCache> getAllManufacturersByBrandsName(String name);

    ManufacturerCache createManufacturer(ManufacturerCache manufacturer);

    ManufacturerCache updateManufacturer(ManufacturerCache manufacturer);

    boolean deleteManufacturerById(String id);

    List<ManufacturerCache> getAllManufacturers();
}
