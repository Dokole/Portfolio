package com.soapdataservice.app.repository;

import com.soapdataservice.app.domain.Category;
import com.soapdataservice.app.domain.MetadataContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findById(Long aLong);

    List<Category> findAll();

    List<Category> findAllByItems_Name(String name);

    List<Category> findAllByItems_Id(Long id);

    List<MetadataContainer> findAllBy();

    boolean existsByName(String name);
}
