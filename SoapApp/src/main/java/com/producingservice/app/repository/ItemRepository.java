package com.producingservice.app.repository;

import com.producingservice.app.domain.Item;
import com.producingservice.app.domain.MetadataContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    Optional<Item> findById(Long aLong);

    List<Item> findAll();

    List<Item> findAllByBrandDetails_Id(Long id);

    List<Item> findAllByManufacturer_Id(Long id);

    List<Item> findAllByBrandDetails_Name(String name);

    List<Item> findAllByManufacturer_Name(String name);

    List<Item> findAllByCategories_Id(Long id);

    List<Item> findAllByCategories_Name(String name);

    List<Item> findAllByDescriptionLike(String desc);

    List<Item> findAllByPriceBetween(int low, int high);

    List<MetadataContainer> findAllBy();

    boolean existsByName(String name);
}
