package com.restservice.app.repository.soapRepository;

import com.restservice.app.dto.database.CreateDataDto;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ObjectCreationDataRepository {
    CreateDataDto getItemCreationData();
    CreateDataDto getBrandCreationData();
    CreateDataDto getManufacturerCreationData();
    CreateDataDto getCategoryCreationData();

}
