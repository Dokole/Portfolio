package com.soapdataservice.app.service.endpoint;


import com.soapdataservice.app.dto.*;

import java.util.List;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface CategoryEndpointService {

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    CategoryDto findByName(String name);

    boolean deleteById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    boolean existsById(Long id);

    List<CategoryDto> findAllByItemsName(String name);

    List<CategoryDto> findAllByItemsId(Long id);
}
