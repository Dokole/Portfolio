package com.transcacheservice.cacheapp.repository.soapRepository;

import com.transcacheservice.cacheapp.dto.database.CreateDataDto;

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
