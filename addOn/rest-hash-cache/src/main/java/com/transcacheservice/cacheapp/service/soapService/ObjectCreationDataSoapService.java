package com.transcacheservice.cacheapp.service.soapService;

import com.transcacheservice.cacheapp.dto.CreationData;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

public interface ObjectCreationDataSoapService {
    CreationData getItemCreationData();
    CreationData getBrandCreationData();
    CreationData getManufacturerCreationData();
    CreationData getCategoryCreationData();

}
