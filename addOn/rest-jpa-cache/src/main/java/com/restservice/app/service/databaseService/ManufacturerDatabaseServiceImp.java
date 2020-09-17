package com.restservice.app.service.databaseService;

import com.restservice.app.exceptions.BadRequestException;
import com.restservice.app.exceptions.NotFoundException;
import com.restservice.app.service.cacheService.ManufacturerCacheService;
import com.restservice.app.domain.cache.redis.containers.ManufacturersCache;
import com.restservice.app.domain.cache.redis.ManufacturerCache;
import com.restservice.app.service.soapService.ManufacturerSoapService;
import com.restservice.app.util.CacheIdNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class ManufacturerDatabaseServiceImp implements ManufacturerDatabaseService {

    private final Logger logger = LoggerFactory.getLogger(ManufacturerDatabaseServiceImp.class);

    private final ManufacturerSoapService manufacturerSoapService;
    private final ManufacturerCacheService manufacturerCacheService;
    private final CacheIdNamer cacheIdNamer;

    @Autowired
    public ManufacturerDatabaseServiceImp(ManufacturerSoapService manufacturerSoapService, ManufacturerCacheService manufacturerCacheService, CacheIdNamer cacheIdNamer) {
        this.manufacturerSoapService = manufacturerSoapService;
        this.manufacturerCacheService = manufacturerCacheService;
        this.cacheIdNamer = cacheIdNamer;
    }

    @Override
    public ManufacturerCache getManufacturerById(String id) {
        try {
            ManufacturersCache manufacturers = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
            if (manufacturers == null) {
                manufacturers = new ManufacturersCache();
                manufacturers.getManufacturers().add(manufacturerSoapService.getManufacturerById(Long.parseLong(id)));
                manufacturers.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
                manufacturerCacheService.save(manufacturers);
            }
            return manufacturers.getManufacturers().get(0);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public ManufacturerCache getManufacturerByName(String name) {
        ManufacturersCache manufacturers = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (manufacturers == null) {
            manufacturers = new ManufacturersCache();
            manufacturers.getManufacturers().add(manufacturerSoapService.getManufacturerByName(name));
            manufacturers.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            manufacturerCacheService.save(manufacturers);
        }
        return manufacturers.getManufacturers().get(0);
    }

    @Override
    public ManufacturerCache getManufacturerByItemId(String id) {
        try {
            ManufacturersCache manufacturers = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
            if (manufacturers == null) {
                manufacturers = new ManufacturersCache();
                manufacturers.getManufacturers().add(manufacturerSoapService.getManufacturerByItemId(Long.parseLong(id)));
                manufacturers.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
                manufacturerCacheService.save(manufacturers);
            }
            return manufacturers.getManufacturers().get(0);
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public ManufacturerCache getManufacturerByItemName(String name) {
        ManufacturersCache manufacturers = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if (manufacturers == null) {
            manufacturers = new ManufacturersCache();
            manufacturers.getManufacturers().add(manufacturerSoapService.getManufacturerByItemName(name));
            manufacturers.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            manufacturerCacheService.save(manufacturers);
        }
        return manufacturers.getManufacturers().get(0);
    }

    @Override
    public ManufacturerCache createManufacturer(ManufacturerCache manufacturer) {

        manufacturerCacheService.invalidateRelatedCaches();
        ManufacturerCache createdManufacturer =
                manufacturerSoapService.createManufacturer(manufacturer);
        manufacturerCacheService.invalidateRelatedCachesDelay();

        return createdManufacturer;
    }

    @Override
    public ManufacturerCache updateManufacturer(ManufacturerCache manufacturer) {

        manufacturerCacheService.invalidateRelatedCaches();
        ManufacturerCache updatedManufacturer =
                manufacturerSoapService.updateManufacturer(manufacturerSoapService.updateManufacturer(manufacturer));
        manufacturerCacheService.invalidateRelatedCachesDelay();

        return updatedManufacturer;
    }

    @Override
    public boolean deleteManufacturerById(String id) {
        manufacturerCacheService.invalidateRelatedCaches();
        try {
            if (manufacturerSoapService.deleteManufacturerById(Long.parseLong(id))) {
                manufacturerCacheService.invalidateRelatedCachesDelay();
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            logger.error("Illegal URI argument = " + id + " Message: " + e.getLocalizedMessage());
            throw new BadRequestException("Illegal URI argument = " + id);
        }
    }

    @Override
    public List<ManufacturerCache> getAllManufacturers() {
        List<ManufacturerCache> results = manufacturerSoapService.getAllManufacturers();
        if(results.isEmpty()) {
            throw new NotFoundException("No manufacturers found");
        }
        return results;
    }

    @Override
    public List<ManufacturerCache> getAllManufacturersByAddressCity(String city) {
        ManufacturersCache manufacturersCache = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + city);
        if(manufacturersCache == null) {
            manufacturersCache = new ManufacturersCache();
            manufacturersCache.setManufacturers(manufacturerSoapService.getManufacturersByAddressCity(city));
            manufacturersCache.setId(cacheIdNamer.getNameOfExecutableMethod() + city);
            manufacturerCacheService.save(manufacturersCache);
        }
        return manufacturersCache.getManufacturers();
    }

    @Override
    public List<ManufacturerCache> getAllManufacturersByAddressCountry(String country) {
        ManufacturersCache manufacturersCache = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + country);
        if(manufacturersCache == null) {
            manufacturersCache = new ManufacturersCache();
            manufacturersCache.setManufacturers(manufacturerSoapService.getManufacturersByAddressCountry(country));
            manufacturersCache.setId(cacheIdNamer.getNameOfExecutableMethod() + country);
            manufacturerCacheService.save(manufacturersCache);
        }
        return manufacturersCache.getManufacturers();
    }

    @Override
    public List<ManufacturerCache> getAllManufacturersByBrandsId(String id) {
        ManufacturersCache manufacturersCache = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + id);
        if(manufacturersCache == null) {
            manufacturersCache = new ManufacturersCache();
            manufacturersCache.setManufacturers(manufacturerSoapService.getManufacturersByBrandsId(Long.parseLong(id)));
            manufacturersCache.setId(cacheIdNamer.getNameOfExecutableMethod() + id);
            manufacturerCacheService.save(manufacturersCache);
        }
        return manufacturersCache.getManufacturers();
    }

    @Override
    public List<ManufacturerCache> getAllManufacturersByBrandsName(String name) {
        ManufacturersCache manufacturersCache = manufacturerCacheService.findCached(cacheIdNamer.getNameOfExecutableMethod() + name);
        if(manufacturersCache == null) {
            manufacturersCache = new ManufacturersCache();
            manufacturersCache.setManufacturers(manufacturerSoapService.getManufacturersByBrandsName(name));
            manufacturersCache.setId(cacheIdNamer.getNameOfExecutableMethod() + name);
            manufacturerCacheService.save(manufacturersCache);
        }
        return manufacturersCache.getManufacturers();
    }

}
