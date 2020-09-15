package com.transcacheservice.cacheapp.repository.soapRepository;

import com.transcacheservice.cacheapp.dto.database.*;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface BrandSoapRepository {

    BrandDto getBrandById(Long id);

    BrandDto getBrandByName(String name);

    BrandDto getBrandByItemId(Long id);

    BrandDto getBrandByItemName(String name);

    List<BrandDto> getAllBrands();

    BrandDto createBrand(BrandDto brand);

    BrandDto updateBrand(BrandDto brand);

    boolean deleteBrandById(Long id);

    List<BrandDto> getBrandsByManufacturersId(Long id);

    List<BrandDto>  getBrandsByManufacturersName(String name);

    List<BrandDto> getBrandsByAddressCountry(String country);

    List<BrandDto> getBrandsByAddressCity(String city);
}
