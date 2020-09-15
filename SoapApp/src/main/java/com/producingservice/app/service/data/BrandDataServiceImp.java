package com.producingservice.app.service.data;

import com.producingservice.app.domain.Brand;
import com.producingservice.app.domain.MetadataContainer;
import com.producingservice.app.repository.BrandRepository;
import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Service("brandDataService")
@Transactional
public class BrandDataServiceImp implements BrandDataService {

    private static final Logger logger = LoggerFactory.getLogger(BrandDataServiceImp.class);
    private final BrandRepository brandRepository;

    @Autowired
    public BrandDataServiceImp(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Brand> findAll() {
        Set<Brand> brands = Sets.newHashSet(brandRepository.findAll());
        return brands;
    }

    @Override
    @Transactional(readOnly = true)
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Brand findByName(String name) {
        return brandRepository.findByName(name).orElse(null);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        brandRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return brandRepository.existsById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Brand findByItemsId(Long id) {
        return brandRepository.findByItems_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Brand findByItemsName(String name) {
        return brandRepository.findByItems_Name(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetadataContainer> findAllIdsAndNames() {
        return brandRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Brand> findAllByManufacturersId(Long id) {
        Set<Brand> brands = Sets.newHashSet(brandRepository.findAllByManufacturers_Id(id));
        return brands;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Brand> findAllByManufacturersName(String name) {
        Set<Brand> brands = Sets.newHashSet(brandRepository.findAllByManufacturers_Name(name));
        return brands;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Brand> findAllByAddressCountry(String country) {
        Set<Brand> brands = Sets.newHashSet(brandRepository.findAllByAddress_Country(country));
        return brands;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Brand> findAllByAddressCity(String city) {
        Set<Brand> brands = Sets.newHashSet(brandRepository.findAllByAddress_City(city));
        return brands;
    }
}
