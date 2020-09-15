package com.producingservice.app.repository;


import com.producingservice.app.domain.Manufacturer;
import com.producingservice.app.domain.MetadataContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Optional<Manufacturer> findByName(String name);

    Optional<Manufacturer> findById(Long aLong);

    Manufacturer findByItems_Id(Long id);

    Manufacturer findByItems_Name(String name);

    List<Manufacturer> findAll();

    List<MetadataContainer> findAllBy();

    List<Manufacturer> findAllByAddress_Country(String country);

    List<Manufacturer> findAllByAddress_City(String city);

    List<Manufacturer> findAllByBrands_Name(String name);

    List<Manufacturer> findAllByBrands_Id(Long id);

    boolean existsByName(String name);


}
