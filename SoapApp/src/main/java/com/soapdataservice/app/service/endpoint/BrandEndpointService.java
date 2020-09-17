package com.soapdataservice.app.service.endpoint;


import com.soapdataservice.app.dto.*;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface BrandEndpointService {

    List<BrandDto> findAll();

    BrandDto findByName(String name);

    BrandDto findById(Long id);

    BrandDto save(BrandDto brandDto);

    BrandDto update(BrandDto brandDto);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    BrandDto findByItemsId(Long id);

    BrandDto findByItemsName(String name);

    List<BrandDto> findAllByManufacturersId(Long id);

    List<BrandDto>  findAllByManufacturersName(String name);

    List<BrandDto> findAllByAddressCountry(String country);

    List<BrandDto> findAllByAddressCity(String city);

}
