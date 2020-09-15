package com.transcacheservice.cacheapp.service.soapService;

import com.transcacheservice.cacheapp.dto.database.BrandDto;
import com.transcacheservice.cacheapp.domain.cache.redis.BrandCache;
import com.transcacheservice.cacheapp.repository.soapRepository.BrandSoapRepository;
import com.transcacheservice.cacheapp.util.CastDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service
public class BrandSoapServiceImp implements BrandSoapService {

    private final Logger logger = LoggerFactory.getLogger(BrandSoapServiceImp.class);

    private final BrandSoapRepository brandSoapRepository;
    private final CastDto castDto;

    @Autowired
    public BrandSoapServiceImp(BrandSoapRepository brandSoapRepository, CastDto castDto) {
        this.brandSoapRepository = brandSoapRepository;
        this.castDto = castDto;
    }

    @Override
    public BrandCache getBrandById(Long id) {
        return castDto.brandFromBrandDto(brandSoapRepository.getBrandById(id));
    }

    @Override
    public BrandCache getBrandByName(String name) {
        return castDto.brandFromBrandDto(brandSoapRepository.getBrandByName(name));
    }

    @Override
    public BrandCache getBrandByItemId(Long id) {
        return castDto.brandFromBrandDto(brandSoapRepository.getBrandByItemId(id));
    }

    @Override
    public BrandCache getBrandByItemName(String name) {
        return castDto.brandFromBrandDto(brandSoapRepository.getBrandByItemName(name));
    }

    @Override
    public List<BrandCache> getAllBrands() {
        List<BrandDto> brandsDto = brandSoapRepository.getAllBrands();
        List<BrandCache> brands = new LinkedList<>();
        brandsDto.forEach(b -> brands.add(castDto.brandFromBrandDto(b)));
        logger.info("IN getAllBrands - {} brands received", brands.size());
        return brands;
    }

    @Override
    public BrandCache createBrand(BrandCache brand) {
        BrandDto brandDto = castDto.brandDtoFromBrand(brand);
        brandDto = brandSoapRepository.createBrand(brandDto);
        return castDto.brandFromBrandDto(brandDto);
    }

    @Override
    public BrandCache updateBrand(BrandCache brand) {
        BrandDto brandDto = castDto.brandDtoFromBrand(brand);
        brandDto = brandSoapRepository.updateBrand(brandDto);
        return castDto.brandFromBrandDto(brandDto);
    }

    @Override
    public boolean deleteBrandById(Long id) {
        return brandSoapRepository.deleteBrandById(id);
    }

    @Override
    public List<BrandCache> getBrandsByManufacturersId(Long id) {
        List<BrandDto> brandsDto = brandSoapRepository.getBrandsByManufacturersId(id);
        List<BrandCache> brands = new LinkedList<>();
        brandsDto.forEach(b -> brands.add(castDto.brandFromBrandDto(b)));
        logger.info("IN getBrandsByManufacturersId - {} brands received", brands.size());
        return brands;
    }

    @Override
    public List<BrandCache> getBrandsByManufacturersName(String name) {
        List<BrandDto> brandsDto = brandSoapRepository.getBrandsByManufacturersName(name);
        List<BrandCache> brands = new LinkedList<>();
        brandsDto.forEach(b -> brands.add(castDto.brandFromBrandDto(b)));
        logger.info("IN getBrandsByManufacturersName - {} brands received", brands.size());
        return brands;
    }

    @Override
    public List<BrandCache> getBrandsByAddressCountry(String country) {
        List<BrandDto> brandsDto = brandSoapRepository.getBrandsByAddressCountry(country);
        List<BrandCache> brands = new LinkedList<>();
        brandsDto.forEach(b -> brands.add(castDto.brandFromBrandDto(b)));
        logger.info("IN getBrandsByAddressCountry - {} brands received", brands.size());
        return brands;
    }

    @Override
    public List<BrandCache> getBrandsByAddressCity(String city) {
        List<BrandDto> brandsDto = brandSoapRepository.getBrandsByAddressCity(city);
        List<BrandCache> brands = new LinkedList<>();
        brandsDto.forEach(b -> brands.add(castDto.brandFromBrandDto(b)));
        logger.info("IN getBrandsByAddressCity - {} brands received", brands.size());
        return brands;
    }
}
