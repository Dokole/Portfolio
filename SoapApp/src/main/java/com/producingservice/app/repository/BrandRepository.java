package com.producingservice.app.repository;

import com.producingservice.app.domain.Brand;
import com.producingservice.app.domain.MetadataContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {


    Optional<Brand> findByName(String name);

    Optional<Brand> findById(Long aLong);

    Brand findByItems_Id(Long id);

    Brand findByItems_Name(String name);

    List<Brand> findAllByManufacturers_Id(Long id);

    List<Brand>  findAllByManufacturers_Name(String name);

    List<Brand> findAllByAddress_Country(String country);

    List<Brand> findAllByAddress_City(String city);

    List<Brand> findAll();

    List<MetadataContainer> findAllBy();

    boolean existsByName(String name);
}
