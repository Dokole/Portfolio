package com.soapdataservice.app.service.data;

import com.soapdataservice.app.domain.Manufacturer;
import com.soapdataservice.app.domain.MetadataContainer;
import com.soapdataservice.app.repository.ManufacturerRepository;
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

@Service("manufacturerDataService")
@Transactional
public class ManufacturerDataServiceImp implements ManufacturerDataService {

    private static Logger logger = LoggerFactory.getLogger(BrandDataServiceImp.class);
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerDataServiceImp(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Manufacturer> findAll(){
        return Sets.newHashSet(manufacturerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Manufacturer findByName(String name) {
        return manufacturerRepository.findByName(name).orElse(null);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public boolean deleteById(Long id) {
        manufacturerRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return manufacturerRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return manufacturerRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Manufacturer findByItemsId(Long id) {
        return manufacturerRepository.findByItems_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Manufacturer findByItemsName(String name) {
        return manufacturerRepository.findByItems_Name(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetadataContainer> findAllIdsAndNames() {
        return manufacturerRepository.findAllBy();
    }

    @Override
    public Set<Manufacturer> findAllByAddressCountry(String country) {
        return Sets.newHashSet(manufacturerRepository.findAllByAddress_Country(country));
    }

    @Override
    public Set<Manufacturer> findAllByAddressCity(String city) {
        return Sets.newHashSet(manufacturerRepository.findAllByAddress_City(city));
    }

    @Override
    public Set<Manufacturer> findAllByBrandsName(String name) {
        return Sets.newHashSet(manufacturerRepository.findAllByBrands_Name(name));
    }

    @Override
    public Set<Manufacturer> findAllByBrandsId(Long id) {
        return Sets.newHashSet(manufacturerRepository.findAllByBrands_Id(id));
    }
}
