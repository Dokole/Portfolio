package com.soapdataservice.app.service.data;

import com.soapdataservice.app.domain.Brand;
import com.soapdataservice.app.domain.MetadataContainer;

import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface BrandDataService {
    Set<Brand> findAll();

    Brand findByName(String name);

    Brand findById(Long id);

    Brand save(Brand brand);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String name);

    Brand findByItemsId(Long id);

    Brand findByItemsName(String name);

    List<MetadataContainer> findAllIdsAndNames();

    Set<Brand> findAllByManufacturersId(Long id);

    Set<Brand>  findAllByManufacturersName(String name);

    Set<Brand> findAllByAddressCountry(String country);

    Set<Brand> findAllByAddressCity(String city);


}
