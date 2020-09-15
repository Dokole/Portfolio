package com.producingservice.app.service.data;

import com.producingservice.app.domain.Manufacturer;
import com.producingservice.app.domain.MetadataContainer;

import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ManufacturerDataService {
    Set<Manufacturer> findAll();

    Manufacturer findById(Long id);

    Manufacturer findByName(String name);

    Manufacturer save(Manufacturer manufacturer);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String name);

    Manufacturer findByItemsId(Long id);

    Manufacturer findByItemsName(String name);

    List<MetadataContainer> findAllIdsAndNames();

    Set<Manufacturer> findAllByAddressCountry(String country);

    Set<Manufacturer> findAllByAddressCity(String city);

    Set<Manufacturer> findAllByBrandsName(String name);

    Set<Manufacturer> findAllByBrandsId(Long id);


}
