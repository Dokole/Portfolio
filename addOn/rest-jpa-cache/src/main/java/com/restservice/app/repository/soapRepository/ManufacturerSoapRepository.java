package com.restservice.app.repository.soapRepository;

import com.restservice.app.dto.database.ManufacturerDto;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ManufacturerSoapRepository {

    ManufacturerDto getManufacturerById(Long id);

    ManufacturerDto getManufacturerByName(String name);

    ManufacturerDto getManufacturerByItemId(Long id);

    ManufacturerDto getManufacturerByItemName(String name);

    List<ManufacturerDto> getAllManufacturers();

    ManufacturerDto createManufacturer(ManufacturerDto manufacturer);

    ManufacturerDto updateManufacturer(ManufacturerDto manufacturer);

    boolean deleteManufacturerById(Long id);

    List<ManufacturerDto> getManufacturersByAddressCountry(String country);

    List<ManufacturerDto> getManufacturersByAddressCity(String city);

    List<ManufacturerDto> getManufacturersByBrandsName(String name);

    List<ManufacturerDto> getManufacturersByBrandsId(Long id);
}
