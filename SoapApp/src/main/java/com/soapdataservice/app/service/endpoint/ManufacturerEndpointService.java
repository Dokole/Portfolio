package com.soapdataservice.app.service.endpoint;

import com.soapdataservice.app.dto.*;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ManufacturerEndpointService {

    List<ManufacturerDto> findAll();

    ManufacturerDto findById(Long id);

    ManufacturerDto findByName(String name);

    boolean deleteById(Long id);

    ManufacturerDto save(ManufacturerDto manufacturerDto);

    ManufacturerDto update(ManufacturerDto manufacturerDto);

    boolean existsById(Long id);

    ManufacturerDto findByItemsId(Long id);

    ManufacturerDto findByItemsName(String name);

    List<ManufacturerDto> findAllByAddressCountry(String country);

    List<ManufacturerDto> findAllByAddressCity(String city);

    List<ManufacturerDto> findAllByBrandsName(String name);

    List<ManufacturerDto> findAllByBrandsId(Long id);
}
