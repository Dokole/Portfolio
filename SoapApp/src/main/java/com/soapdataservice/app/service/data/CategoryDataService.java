package com.soapdataservice.app.service.data;

import com.soapdataservice.app.domain.Category;
import com.soapdataservice.app.domain.MetadataContainer;

import java.util.List;
import java.util.Set;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface CategoryDataService {
    Set<Category> findAll();

    Category findById(Long id);

    Category findByName(String name);

    boolean deleteById(Long id);

    Category save(Category category);

    boolean existsById(Long id);

    boolean existsByName(String name);

    List<MetadataContainer> findAllIdsAndNames();

    Set<Category> findAllByItemsName(String name);

    Set<Category> findAllByItemsId(Long id);

}
